use std::io::Write;

#[derive(Debug)]
pub struct ApiVersionsRequest {
    api_version: i16,
    correlation_id: i32,
    client_id: String,
    client_software_name: String,
    client_software_version: String,
}

impl ApiVersionsRequest {
    pub fn new(
        correlation_id: i32,
        client_id: impl Into<String>,
        client_software_name: impl Into<String>,
        client_software_version: impl Into<String>,
    ) -> Self {
        ApiVersionsRequest {
            api_version: 3,
            correlation_id,
            client_id: client_id.into(),
            client_software_name: client_software_name.into(),
            client_software_version: client_software_version.into(),
        }
    }

    pub fn encode(&self) -> Result<Vec<u8>, std::io::Error> {
        let mut bytes = Vec::new();

        let name_bytes = encode_compact_string(&self.client_software_name)?;
        let version_bytes = encode_compact_string(&self.client_software_version)?;
        let length: i32 =
            (2 + 2 + 4 + 2 + self.client_id.len() + 1 + name_bytes.len() + version_bytes.len() + 1)
                as i32;

        // Length
        bytes.write_all(&length.to_be_bytes())?;

        // Header
        bytes.write_all(&18i16.to_be_bytes())?;
        bytes.write_all(&self.api_version.to_be_bytes())?;
        bytes.write_all(&self.correlation_id.to_be_bytes())?;

        let client_id_length: i16 = self.client_id.len() as i16;
        bytes.write_all(&client_id_length.to_be_bytes())?;
        bytes.extend_from_slice(self.client_id.as_bytes());

        // Tag buffer
        bytes.write_all(&0u8.to_be_bytes())?;

        // Body
        bytes.write_all(&name_bytes)?;
        bytes.write_all(&version_bytes)?;

        // Tag buffer
        bytes.write_all(&0u8.to_be_bytes())?;

        Ok(bytes)
    }
}

#[derive(Debug)]
pub struct ApiVersionsResponse {
    pub correlation_id: i32,
    pub throttle_time_ms: i32,
    pub error_code: i16,
    pub api_keys: Vec<ApiKey>,
}

#[derive(Debug)]
pub struct ApiKey {
    pub api_key: i16,
    pub min_version: i16,
    pub max_version: i16,
}

#[derive(Debug)]
pub struct MetadataRequest {
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
        bytes.write_all(&3i16.to_be_bytes())?;
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

#[derive(Debug, Clone, PartialEq)]
pub struct MetadataResponse {
    correlation_id: i32,
    throttle_time_ms: i32,
    brokers: Vec<Broker>,
    cluster_id: Option<String>,
    controller_id: i32,
    topics: Vec<Topic>,
}

#[derive(Debug, Clone, PartialEq)]
pub struct Broker {
    node_id: i32,
    host: String,
    port: i32,
    rack: Option<String>,
}

pub type Uuid = [u8; 16];

#[derive(Debug, Clone, PartialEq)]
pub struct Topic {
    error_code: i16,
    name: Option<String>,
    topic_id: Uuid,
    is_internal: bool,
    partitions: Vec<Partition>,
    topic_authorized_operations: u32,
}

#[derive(Debug, Clone, PartialEq)]
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
struct TaggedField {
    key: u64,
    value: Vec<u8>,
}

#[derive(Debug)]
pub enum ParserError {
    Unknown,
    /// The size of the message as indicated in the first 4 bytes is larger than
    /// the byte buffer we want to parse.
    IncompleteMessage,
    InvalidEncoding,
}

pub type ParserResult<T> = Result<T, ParserError>;

pub struct Parser<'a> {
    /// Current location in the buffer
    current: usize,
    bytes: &'a [u8],
}

/// Total size of messsage including the 4 bytes for the size prefix. Note that bytes may be longer.
fn parse_message_size(bytes: &[u8]) -> ParserResult<usize> {
    if bytes.len() < 4 {
        return Err(ParserError::IncompleteMessage);
    }

    let size = i32::from_be_bytes([bytes[0], bytes[1], bytes[2], bytes[3]]);
    let num_bytes = size as usize + 4;
    if bytes.len() < num_bytes {
        return Err(ParserError::IncompleteMessage);
    }

    Ok(num_bytes)
}

impl<'a> Parser<'a> {
    pub fn parse_metadata_response(bytes: &[u8]) -> Result<(MetadataResponse, usize), ParserError> {
        let num_bytes = parse_message_size(bytes)?;
        let mut parser = Parser::new(&bytes[4..num_bytes]);
        let reponse = parser.parse_metadata_response_inner()?;
        Ok((reponse, num_bytes))
    }

    pub fn parse_api_versions_response(
        bytes: &[u8],
    ) -> Result<(ApiVersionsResponse, usize), ParserError> {
        let num_bytes = parse_message_size(bytes)?;
        let mut parser = Parser::new(&bytes[4..num_bytes]);
        let response = parser.parse_api_versions_response_inner()?;
        Ok((response, num_bytes))
    }

    fn new(bytes: &'a [u8]) -> Self {
        Parser { current: 0, bytes }
    }

    fn parse_metadata_response_inner(&mut self) -> ParserResult<MetadataResponse> {
        let correlation_id = self.parse_i32()?;
        self.parse_tagged_fields()?;

        let throttle_time_ms = self.parse_i32()?;
        let brokers = self.parse_brokers()?;
        let cluster_id = self.parse_compact_nullable_string()?;
        let controller_id = self.parse_i32()?;
        let topics = self.parse_topics()?;
        self.parse_tagged_fields()?;

        Ok(MetadataResponse {
            correlation_id,
            throttle_time_ms,
            brokers,
            cluster_id,
            controller_id,
            topics,
        })
    }

    fn parse_api_versions_response_inner(&mut self) -> ParserResult<ApiVersionsResponse> {
        let correlation_id = self.parse_i32()?;
        let error_code = self.parse_i16()?;
        let api_keys = self.parse_api_keys()?;
        let throttle_time_ms = self.parse_i32()?;

        self.parse_tagged_fields()?;

        Ok(ApiVersionsResponse {
            correlation_id,
            throttle_time_ms,
            error_code,
            api_keys,
        })
    }

    fn parse_api_keys(&mut self) -> ParserResult<Vec<ApiKey>> {
        let n = self.parse_varint()?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let api_keys_count = n - 1;
        let mut api_keys = Vec::with_capacity(api_keys_count as usize);

        for _ in 0..api_keys_count {
            api_keys.push(self.parse_api_key()?);
        }

        Ok(api_keys)
    }

    fn parse_api_key(&mut self) -> ParserResult<ApiKey> {
        let api_key = self.parse_i16()?;
        let min_version = self.parse_i16()?;
        let max_version = self.parse_i16()?;
        self.parse_tagged_fields()?;

        Ok(ApiKey {
            api_key,
            min_version,
            max_version,
        })
    }

    fn parse_topics(&mut self) -> ParserResult<Vec<Topic>> {
        let n = self.parse_varint()?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let topics_count = n - 1;
        let mut topics = Vec::with_capacity(topics_count as usize);

        for _ in 0..topics_count {
            topics.push(self.parse_topic()?);
        }

        Ok(topics)
    }

    fn parse_topic(&mut self) -> ParserResult<Topic> {
        let error_code = self.parse_i16()?;
        let name = self.parse_compact_nullable_string()?;
        let topic_id = self.parse_uuid()?;
        let is_internal = self.parse_boolean()?;
        let partitions = self.parse_partitions()?;
        let topic_authorized_operations = self.parse_u32()?;
        self.parse_tagged_fields()?;

        Ok(Topic {
            error_code,
            name,
            topic_id,
            is_internal,
            partitions,
            topic_authorized_operations,
        })
    }

    fn parse_partitions(&mut self) -> ParserResult<Vec<Partition>> {
        let n = self.parse_varint()?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let partitions_count = n - 1;
        let mut partitions = Vec::with_capacity(partitions_count as usize);

        for _ in 0..partitions_count {
            partitions.push(self.parse_partition()?);
        }

        Ok(partitions)
    }

    fn parse_partition(&mut self) -> ParserResult<Partition> {
        let error_code = self.parse_i16()?;
        let partition_index = self.parse_i32()?;
        let leader_id = self.parse_i32()?;
        let leader_epoch = self.parse_i32()?;
        let replica_nodes = self.parse_i32_compact_array()?;
        let isr_nodes = self.parse_i32_compact_array()?;
        let offline_replicas = self.parse_i32_compact_array()?;
        self.parse_tagged_fields()?;

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

    fn parse_i32_compact_array(&mut self) -> ParserResult<Vec<i32>> {
        let n = self.parse_varint()?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let count = n - 1;
        let mut numbers = Vec::with_capacity(count as usize);

        for _ in 0..count {
            numbers.push(self.parse_i32()?);
        }

        Ok(numbers)
    }

    fn parse_uuid(&mut self) -> ParserResult<Uuid> {
        let mut uuid: Uuid = [0u8; 16];
        uuid.copy_from_slice(&self.bytes[self.current..(self.current + 16)]);
        self.current += 16;
        Ok(uuid)
    }

    fn parse_tagged_fields(&mut self) -> ParserResult<Vec<TaggedField>> {
        let num_fields = self.parse_varint()?;
        if num_fields == 0 {
            return Ok(vec![]);
        }

        let mut fields = Vec::with_capacity(num_fields as usize);
        for _ in 0..num_fields {
            let key = self.parse_varint()?;
            let value = self.parse_bytes()?;
            fields.push(TaggedField { key, value });
        }

        Ok(fields)
    }

    fn parse_bytes(&mut self) -> ParserResult<Vec<u8>> {
        let n = self.parse_varint()?;
        let num_bytes = n as usize;
        let bytes = &self.bytes[self.current..(self.current + num_bytes)];
        self.current += num_bytes;
        Ok(bytes.to_vec())
    }

    fn parse_brokers(&mut self) -> ParserResult<Vec<Broker>> {
        let n = self.parse_varint()?;
        if n <= 1 {
            return Ok(vec![]);
        }

        let brokers_count = n - 1;
        let mut brokers = Vec::with_capacity(brokers_count as usize);

        for _ in 0..brokers_count {
            brokers.push(self.parse_broker()?);
        }

        Ok(brokers)
    }

    fn parse_broker(&mut self) -> ParserResult<Broker> {
        let node_id = self.parse_i32()?;
        let host = self.parse_compact_string()?;
        let port = self.parse_i32()?;
        let rack = self.parse_compact_nullable_string()?;
        self.parse_tagged_fields()?;

        Ok(Broker {
            node_id,
            host,
            port,
            rack,
        })
    }

    fn parse_compact_string(&mut self) -> ParserResult<String> {
        let n = self.parse_varint()?;
        let num_bytes = n - 1;
        let utf8_bytes = &self.bytes[self.current..(self.current + (num_bytes as usize))];
        self.current += num_bytes as usize;
        Ok(String::from_utf8_lossy(utf8_bytes).to_string())
    }

    fn parse_compact_nullable_string(&mut self) -> ParserResult<Option<String>> {
        let n = self.parse_varint()?;
        if n == 0 {
            return Ok(None);
        }

        let num_bytes = n - 1;
        let utf8_bytes = &self.bytes[self.current..(self.current + (num_bytes as usize))];
        self.current += num_bytes as usize;
        Ok(Some(String::from_utf8_lossy(utf8_bytes).to_string()))
    }

    fn parse_boolean(&mut self) -> ParserResult<bool> {
        Ok(self.next_byte() != 0)
    }

    fn parse_i16(&mut self) -> ParserResult<i16> {
        Ok(i16::from_be_bytes([self.next_byte(), self.next_byte()]))
    }

    fn parse_i32(&mut self) -> ParserResult<i32> {
        Ok(i32::from_be_bytes([
            self.next_byte(),
            self.next_byte(),
            self.next_byte(),
            self.next_byte(),
        ]))
    }

    fn parse_u32(&mut self) -> ParserResult<u32> {
        Ok(u32::from_be_bytes([
            self.next_byte(),
            self.next_byte(),
            self.next_byte(),
            self.next_byte(),
        ]))
    }

    fn parse_varint(&mut self) -> ParserResult<u64> {
        match parse_varint(&self.bytes[self.current..]) {
            Ok((value, num_bytes)) => {
                self.current += num_bytes;
                Ok(value)
            }
            Err(_) => Err(ParserError::InvalidEncoding),
        }
    }

    fn next_byte(&mut self) -> u8 {
        let byte = self.bytes[self.current];
        self.current += 1;
        byte
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

/// Encodes a u64 into a Protocol Buffers varint.
pub fn encode_varint(mut value: u64) -> Vec<u8> {
    let mut bytes = Vec::new();

    // Continue encoding until the remaining value fits in 7 bits.
    while value >= 0x80 {
        // Take the lower 7 bits, set the MSB to indicate continuation,
        // and push the byte into the buffer.
        bytes.push((value as u8 & 0x7F) | 0x80);
        // Shift right by 7 bits to process the next chunk.
        value >>= 7;
    }

    // The final byte (with MSB = 0) holds the remaining value.
    bytes.push(value as u8);
    bytes
}

pub fn encode_compact_string(string: &str) -> Result<Vec<u8>, std::io::Error> {
    let mut bytes = Vec::new();
    let length = encode_varint((string.len() + 1) as u64);
    bytes.write_all(&length)?;
    bytes.write_all(string.as_bytes())?;
    Ok(bytes)
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
        fn test_parse_and_encode_varint(number in any::<u64>()) {
            let bytes = encode_varint(number);
            let (parsed_number, _) = parse_varint(&bytes).unwrap();
            assert_eq!(number, parsed_number);
        }

        #[test]
        fn test_parse_metadata_response_doesnt_crash(bytes in any::<Vec<u8>>()) {
            let _ = Parser::parse_metadata_response(&bytes);
        }

        #[test]
        fn test_parse_api_versions_response_doesnt_crash(bytes in any::<Vec<u8>>()) {
            let _ = Parser::parse_api_versions_response(&bytes);
        }
    }
}
