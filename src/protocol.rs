use std::io::Write;

#[derive(Debug)]
pub struct MetadataRequest {
    pub api_key: i16,
    pub api_version: i16,
    pub correlation_id: i32,
    pub client_id: String,
    pub topics: Vec<(Uuid, String)>,
    pub allow_auto_topic_creation: bool,
    pub include_topic_authorized_operations: bool,
}

impl MetadataRequest {
    pub fn new(correlation_id: i32, client_id: impl Into<String>) -> Self {
        MetadataRequest {
            api_key: 3,
            api_version: 12,
            correlation_id,
            client_id: client_id.into(),
            topics: vec![],
            allow_auto_topic_creation: false,
            include_topic_authorized_operations: false,
        }
    }

    pub fn length(&self) -> i32 {
        (2 + 2 + 4 + 2 + self.client_id.len() + 4 + 2) as i32
    }

    pub fn encode(&self) -> Result<Vec<u8>, std::io::Error> {
        let mut bytes = Vec::new();

        // Length
        bytes.write_all(&self.length().to_be_bytes())?;

        // Header
        bytes.write_all(&self.api_key.to_be_bytes())?;
        bytes.write_all(&self.api_version.to_be_bytes())?;
        bytes.write_all(&self.correlation_id.to_be_bytes())?;

        let client_id_length: i16 = self.client_id.len() as i16;
        bytes.write_all(&client_id_length.to_be_bytes())?;
        bytes.extend_from_slice(self.client_id.as_bytes());

        // Request body
        let num_topics: i32 = self.topics.len() as i32;
        bytes.write_all(&num_topics.to_be_bytes())?;
        bytes.push(0u8);
        bytes.push(0u8);

        Ok(bytes)
    }
}

#[derive(Debug)]
pub struct MetadataResponse {
    correlation_id: i32,
    throttle_time_ms: i32,
    brokers: Vec<Broker>,
    cluster_id: Option<String>,
    controller_id: i32,
    topics: Vec<Topic>,
}

#[derive(Debug)]
pub struct Broker {
    node_id: i32,
    host: String,
    port: i32,
    rack: Option<String>,
}

pub type Uuid = [u8; 16];

#[derive(Debug)]
pub struct Topic {
    error_code: i16,
    name: Option<String>,
    topic_id: Uuid,
    is_internal: bool,
    partitions: Vec<Partition>,
    topic_authorized_operations: u32,
}

#[derive(Debug)]
pub struct Partition {
    error_code: i16,
    partition_index: i32,
    leader_id: i32,
    leader_epoch: i32,
    replica_nodes: Vec<i32>,
    isr_nodes: Vec<i32>,
    offline_replicas: Vec<i32>,
}

#[derive(Debug)]
pub enum ParserError {
    Unknown,
    /// The size of the message as indicated in the first 4 bytes is larger than
    /// the byte buffer we want to parse.
    IncompleteMessage,
    NotEnoughBytes,
    InvalidEncoding,
}

pub type ParserResult<T> = Result<T, ParserError>;

pub struct Parser {
    /// Current location in the buffer
    current: usize,
    /// How many bytes are left
    size: i32,
}

impl Parser {
    pub fn parse(bytes: &[u8]) -> (ParserResult<MetadataResponse>, usize) {
        if bytes.len() < 4 {
            return (Err(ParserError::IncompleteMessage), 0);
        }

        let size = i32::from_be_bytes([bytes[0], bytes[1], bytes[2], bytes[3]]);
        let num_bytes = size as usize + 4;
        if bytes.len() < num_bytes {
            return (Err(ParserError::IncompleteMessage), 0);
        }

        let mut parser = Parser::new(size);
        (
            parser.parse_metadata_response(&bytes[4..num_bytes]),
            num_bytes,
        )
    }

    fn new(size: i32) -> Self {
        Parser { current: 0, size }
    }

    pub fn parse_metadata_response(&mut self, bytes: &[u8]) -> ParserResult<MetadataResponse> {
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
            Err(_) => Err(ParserError::InvalidEncoding),
        }
    }

    fn next_byte(&mut self, bytes: &[u8]) -> Result<u8, ParserError> {
        let byte = bytes[self.current];
        self.current += 1;

        if self.current == self.size as usize {
            return Err(ParserError::NotEnoughBytes);
        }

        Ok(byte)
    }
}

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

#[cfg(test)]
mod tests {
    use super::*;
    use proptest::prelude::*;

    #[test]
    fn test_parse_varint_single_byte() {
        let bytes = [0x05];
        let result = parse_varint(&bytes);
        assert_eq!(result, Ok((5, 1)));
    }

    #[test]
    fn test_parse_varint_multiple_bytes() {
        let bytes = [0xAC, 0x02];
        let result = parse_varint(&bytes);
        assert_eq!(result, Ok((300, 2)));
    }

    #[test]
    fn test_parse_varint_incomplete() {
        let bytes = [0xAC];
        let result = parse_varint(&bytes);
        assert_eq!(result, Err("incomplete varint"));
    }

    proptest! {
        #[test]
        fn test_metadata_request_length(correlation_id in any::<i32>(), client_id in any::<String>()) {
            let request = MetadataRequest::new(correlation_id, client_id);
            let bytes = request.encode().expect("encoding failed");

            // The complete message has 4 bytes for indicating the length
            // followed by the actual message (of length request.length()).
            assert_eq!(bytes.len() - 4, request.length() as usize)
        }

        #[test]
        fn test_varint_doesnt_crash(bytes in any::<Vec<u8>>()) {
            let _ = parse_varint(&bytes);
        }

        #[test]
        fn test_parser_doesnt_crash(bytes in any::<Vec<u8>>()) {
            let _ = Parser::parse(&bytes);
        }
    }
}
