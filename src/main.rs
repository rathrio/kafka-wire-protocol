use std::io::{Read, Write};
use std::net::TcpStream;

fn main() -> std::io::Result<()> {
    let mut stream = TcpStream::connect("127.0.0.1:9092")?;
    println!("Connected to server!");

    let request = build_metadata_request();
    stream.write_all(&request)?;
    println!("Sent metadata request");

    let mut buffer = [0; 64];
    let mut bytes = Vec::new();
    loop {
        let bytes_read = stream.read(&mut buffer)?;
        // End of stream.
        if bytes_read == 0 {
            break;
        }
        bytes.extend_from_slice(&buffer[..bytes_read]);

        if let (Ok(response), bytes_consumed) = parse_metadata_response(&bytes) {
            bytes.drain(..bytes_consumed);
            println!("Received complete response");
            println!("{:#?}", &response);

            // For the metadata case we can exit.
            break;
        } else {
            println!("Not enough bytes")
        }
    }

    println!("Connection closed");
    Ok(())
}

fn parse_metadata_response(response: &[u8]) -> (ParserResult<MetadataResponse>, usize) {
    if response.len() < 4 {
        return (Err(ParserError::NotEnoughBytes), 0);
    }

    let size = i32::from_be_bytes([response[0], response[1], response[2], response[3]]);
    let num_bytes = size as usize + 4;
    if response.len() < num_bytes {
        return (Err(ParserError::NotEnoughBytes), 0);
    }

    let mut parser = Parser::new(size);
    (
        parser.parse_metadata_response(&response[4..num_bytes]),
        num_bytes,
    )
}

// TODO: extract to module and add some unit tests
//
// https://protobuf.dev/programming-guides/encoding/#varints
fn parse_varint(bytes: &[u8]) -> Result<(u64, usize), &'static str> {
    let mut result: u64 = 0;
    let mut shift = 0;

    for (i, &byte) in bytes.iter().enumerate() {
        // Extract lower 7 bits.
        let value = (byte & 0x7F) as u64;

        // Add extracted bits to result. Shift left based on which byte we are
        // considering.
        result |= value << shift;

        // If MSB is 0, we're done, otherwise we need to parse the next byte.
        if byte & 0x80 == 0 {
            return Ok((result, i + 1));
        }

        shift += 7;

        if shift >= 64 {
            return Err("varint is too long overflows a 64-bit integer");
        }
    }

    Err("incomplete varint")
}

fn hex_dump(bytes: &[u8]) {
    for chunk in bytes.chunks(16) {
        print!("{:08x}: ", chunk.as_ptr() as usize);
        for byte in chunk {
            print!("{:02x} ", byte);
        }
        println!();
    }
}

fn build_metadata_request() -> Vec<u8> {
    let mut request = Vec::new();

    let api_key: i16 = 3;
    let api_version: i16 = 12;
    let correlation_id: i32 = 42;
    let client_id_bytes = "rust-client".as_bytes();
    let client_id_length: i16 = client_id_bytes.len() as i16;
    let topics_count: i32 = 0; // Empty array to retrieve all topics
    let allow_auto_topic_creation: u8 = 0;
    let include_topic_authorized_operations: u8 = 0;

    let request_length: i32 = (2 + 2 + 4 + 2 + client_id_length + 4 + 2) as i32;

    // Length
    request.write_all(&request_length.to_be_bytes()).unwrap();

    // Header
    request.write_all(&api_key.to_be_bytes()).unwrap();
    request.write_all(&api_version.to_be_bytes()).unwrap();
    request.write_all(&correlation_id.to_be_bytes()).unwrap();

    // Client ID
    request.write_all(&client_id_length.to_be_bytes()).unwrap();
    request.extend_from_slice(client_id_bytes);

    // Request body (topic array)
    request.write_all(&topics_count.to_be_bytes()).unwrap();
    request.push(allow_auto_topic_creation);
    request.push(include_topic_authorized_operations);

    request
}

#[derive(Debug)]
struct MetadataResponse {
    correlation_id: i32,
    throttle_time_ms: i32,
    brokers: Vec<Broker>,
    cluster_id: Option<String>,
    controller_id: i32,
    topics: Vec<Topic>,
}

#[derive(Debug)]
struct Broker {
    node_id: i32,
    host: String,
    port: i32,
    rack: Option<String>,
}

type Uuid = [u8; 16];

#[derive(Debug)]
struct Topic {
    error_code: i16,
    name: Option<String>,
    topic_id: Uuid,
    is_internal: bool,
    partitions: Vec<Partition>,
    topic_authorized_operations: u32,
}

#[derive(Debug)]
struct Partition {
    error_code: i16,
    partition_index: i32,
    leader_id: i32,
    leader_epoch: i32,
    replica_nodes: Vec<i32>,
    isr_nodes: Vec<i32>,
    offline_replicas: Vec<i32>,
}

#[derive(Debug)]
enum ParserError {
    Unknown,
    NotEnoughBytes,
}

type ParserResult<T> = Result<T, ParserError>;

struct Parser {
    /// Current location in the buffer
    current: usize,
    /// How many bytes are left
    size: i32,
}

impl Parser {
    fn new(size: i32) -> Self {
        Parser { current: 0, size }
    }

    fn parse_metadata_response(&mut self, bytes: &[u8]) -> ParserResult<MetadataResponse> {
        let correlation_id = self.parse_i32(bytes)?;
        self.parse_tagged_fields(bytes)?;

        let throttle_time_ms = self.parse_i32(bytes)?;
        let brokers = self.parse_brokers(bytes)?;
        let cluster_id = self.parse_compact_nullable_string(bytes)?;
        let controller_id = self.parse_i32(bytes)?;
        let topics = self.parse_topics(bytes)?;
        self.parse_tagged_fields(bytes)?;

        Ok(MetadataResponse {
            correlation_id,
            throttle_time_ms,
            brokers,
            cluster_id,
            controller_id,
            topics,
        })
    }

    fn parse_topics(&mut self, bytes: &[u8]) -> ParserResult<Vec<Topic>> {
        let n = self.parse_varint(bytes)?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let topics_count = n - 1;
        let mut topics = Vec::with_capacity(topics_count as usize);

        for _ in 0..topics_count {
            topics.push(self.parse_topic(bytes)?);
        }

        Ok(topics)
    }

    fn parse_topic(&mut self, bytes: &[u8]) -> ParserResult<Topic> {
        let error_code = self.parse_i16(bytes)?;
        let name = self.parse_compact_nullable_string(bytes)?;
        let topic_id = self.parse_uuid(bytes)?;
        let is_internal = self.parse_boolean(bytes)?;
        let partitions = self.parse_partitions(bytes)?;
        let topic_authorized_operations = self.parse_u32(bytes)?;
        self.parse_tagged_fields(bytes)?;

        Ok(Topic {
            error_code,
            name,
            topic_id,
            is_internal,
            partitions,
            topic_authorized_operations,
        })
    }

    fn parse_partitions(&mut self, bytes: &[u8]) -> ParserResult<Vec<Partition>> {
        let n = self.parse_varint(bytes)?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let partitions_count = n - 1;
        let mut partitions = Vec::with_capacity(partitions_count as usize);

        for _ in 0..partitions_count {
            partitions.push(self.parse_partition(bytes)?);
        }

        Ok(partitions)
    }

    fn parse_partition(&mut self, bytes: &[u8]) -> ParserResult<Partition> {
        let error_code = self.parse_i16(bytes)?;
        let partition_index = self.parse_i32(bytes)?;
        let leader_id = self.parse_i32(bytes)?;
        let leader_epoch = self.parse_i32(bytes)?;
        let replica_nodes = self.parse_i32_compact_array(bytes)?;
        let isr_nodes = self.parse_i32_compact_array(bytes)?;
        let offline_replicas = self.parse_i32_compact_array(bytes)?;
        self.parse_tagged_fields(bytes)?;

        Ok(Partition {
            error_code,
            partition_index,
            leader_id,
            leader_epoch,
            replica_nodes,
            isr_nodes,
            offline_replicas,
        })
    }

    fn parse_i32_compact_array(&mut self, bytes: &[u8]) -> ParserResult<Vec<i32>> {
        let n = self.parse_varint(bytes)?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let count = n - 1;
        let mut numbers = Vec::with_capacity(count as usize);

        for _ in 0..count {
            numbers.push(self.parse_i32(bytes)?);
        }

        Ok(numbers)
    }

    fn parse_uuid(&mut self, bytes: &[u8]) -> ParserResult<Uuid> {
        let mut uuid: Uuid = [0u8; 16];
        uuid.copy_from_slice(&bytes[self.current..(self.current + 16)]);
        self.current += 16;
        Ok(uuid)
    }

    fn parse_tagged_fields(&mut self, bytes: &[u8]) -> ParserResult<()> {
        let num_fields = self.parse_varint(bytes)?;
        if num_fields == 0 {
            return Ok(());
        }

        todo!("Tagged fields not supported yet")
    }

    fn parse_brokers(&mut self, bytes: &[u8]) -> ParserResult<Vec<Broker>> {
        let n = self.parse_varint(bytes)?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let brokers_count = n - 1;
        let mut brokers = Vec::with_capacity(brokers_count as usize);

        for _ in 0..brokers_count {
            brokers.push(self.parse_broker(bytes)?);
        }

        Ok(brokers)
    }

    fn parse_broker(&mut self, bytes: &[u8]) -> ParserResult<Broker> {
        let node_id = self.parse_i32(bytes)?;
        let host = self.parse_compact_string(bytes)?;
        let port = self.parse_i32(bytes)?;
        let rack = self.parse_compact_nullable_string(bytes)?;
        self.parse_tagged_fields(bytes)?;

        Ok(Broker {
            node_id,
            host,
            port,
            rack,
        })
    }

    fn parse_compact_string(&mut self, bytes: &[u8]) -> ParserResult<String> {
        let n = self.parse_varint(bytes)?;
        let num_bytes = n - 1;
        let utf8_bytes = &bytes[self.current..(self.current + (num_bytes as usize))];
        self.current += num_bytes as usize;
        Ok(String::from_utf8_lossy(utf8_bytes).to_string())
    }

    fn parse_compact_nullable_string(&mut self, bytes: &[u8]) -> ParserResult<Option<String>> {
        let n = self.parse_varint(bytes)?;
        if n == 0 {
            return Ok(None);
        }

        let num_bytes = n - 1;
        let utf8_bytes = &bytes[self.current..(self.current + (num_bytes as usize))];
        self.current += num_bytes as usize;
        Ok(Some(String::from_utf8_lossy(utf8_bytes).to_string()))
    }

    fn parse_boolean(&mut self, bytes: &[u8]) -> ParserResult<bool> {
        Ok(self.next_byte(bytes)? != 0)
    }

    fn parse_i16(&mut self, bytes: &[u8]) -> ParserResult<i16> {
        Ok(i16::from_be_bytes([
            self.next_byte(bytes)?,
            self.next_byte(bytes)?,
        ]))
    }

    fn parse_i32(&mut self, bytes: &[u8]) -> ParserResult<i32> {
        Ok(i32::from_be_bytes([
            self.next_byte(bytes)?,
            self.next_byte(bytes)?,
            self.next_byte(bytes)?,
            self.next_byte(bytes)?,
        ]))
    }

    fn parse_u32(&mut self, bytes: &[u8]) -> ParserResult<u32> {
        Ok(u32::from_be_bytes([
            self.next_byte(bytes)?,
            self.next_byte(bytes)?,
            self.next_byte(bytes)?,
            self.next_byte(bytes)?,
        ]))
    }

    fn parse_varint(&mut self, bytes: &[u8]) -> ParserResult<u64> {
        match parse_varint(&bytes[self.current..]) {
            Ok((value, num_bytes)) => {
                self.current += num_bytes;
                Ok(value)
            }
            Err(_) => Err(ParserError::Unknown),
        }
    }

    fn next_byte(&mut self, bytes: &[u8]) -> Result<u8, ParserError> {
        let byte = bytes[self.current];
        self.current += 1;

        if self.current == self.size as usize {
            return Err(ParserError::Unknown);
        }

        Ok(byte)
    }
}
