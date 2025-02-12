use std::io::{Read, Write};
use std::net::TcpStream;

fn main() -> std::io::Result<()> {
    let mut stream = TcpStream::connect("127.0.0.1:9092")?;
    println!("Connected to server!");

    let request = build_metadata_request();
    stream.write_all(&request)?;
    println!("Sent metadata request");

    let mut buffer = [0; 4096];
    let mut response = Vec::new();
    loop {
        let bytes_read = stream.read(&mut buffer)?;
        if bytes_read == 0 {
            break;
        }

        response.extend_from_slice(&buffer[..bytes_read]);
        // If we did not completely fill up the buffer, we have read the full response.
        if bytes_read < buffer.len() {
            break;
        }
    }

    if response.is_empty() {
        println!("No response received");
    } else {
        println!("Received response:");
        let metadata_response = parse_metadata_response(&response).unwrap();
        println!("{:#?}", metadata_response);
    }

    Ok(())
}

#[derive(Debug)]
struct MetadataResponse {
    correlation_id: i32,
    throttle_time_ms: i32,
    brokers: Vec<Broker>,
    cluster_id: Option<String>,
    controller_id: i32,
}

#[derive(Debug)]
struct Broker {
    node_id: i32,
    host: String,
    port: i32,
    rack: Option<String>,
}

#[derive(Debug)]
struct ParserError {}

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

    // Metadata Response (Version: 12) => throttle_time_ms [brokers] cluster_id controller_id [topics] TAG_BUFFER
    //   throttle_time_ms => INT32
    //   brokers => node_id host port rack TAG_BUFFER
    //     node_id => INT32
    //     host => COMPACT_STRING
    //     port => INT32
    //     rack => COMPACT_NULLABLE_STRING
    //   cluster_id => COMPACT_NULLABLE_STRING
    //   controller_id => INT32
    //   topics => error_code name topic_id is_internal [partitions] topic_authorized_operations TAG_BUFFER
    //     error_code => INT16
    //     name => COMPACT_NULLABLE_STRING
    //     topic_id => UUID
    //     is_internal => BOOLEAN
    //     partitions => error_code partition_index leader_id leader_epoch [replica_nodes] [isr_nodes] [offline_replicas] TAG_BUFFER
    //       error_code => INT16
    //       partition_index => INT32
    //       leader_id => INT32
    //       leader_epoch => INT32
    //       replica_nodes => INT32
    //       isr_nodes => INT32
    //       offline_replicas => INT32
    //     topic_authorized_operations => INT32
    fn parse_metadata_response(&mut self, bytes: &[u8]) -> ParserResult<MetadataResponse> {
        let correlation_id = self.parse_i32(bytes)?;
        self.parse_tagged_fields(bytes)?;

        let throttle_time_ms = self.parse_i32(bytes)?;
        let brokers = self.parse_brokers(bytes)?;
        let cluster_id = self.parse_compact_nullable_string(bytes)?;
        let controller_id = self.parse_i32(bytes)?;

        Ok(MetadataResponse {
            correlation_id,
            throttle_time_ms,
            brokers,
            cluster_id,
            controller_id,
        })
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

    fn parse_i32(&mut self, bytes: &[u8]) -> ParserResult<i32> {
        Ok(i32::from_be_bytes([
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
            Err(_) => Err(ParserError {}),
        }
    }

    fn next_byte(&mut self, bytes: &[u8]) -> Result<u8, ParserError> {
        let byte = bytes[self.current];
        self.current += 1;

        if self.current == self.size as usize {
            return Err(ParserError {});
        }

        Ok(byte)
    }
}

fn parse_metadata_response(response: &[u8]) -> ParserResult<MetadataResponse> {
    // get first 4 bytes
    let size = i32::from_be_bytes([response[0], response[1], response[2], response[3]]);
    let mut parser = Parser::new(size);

    parser.parse_metadata_response(&response[4..])
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
