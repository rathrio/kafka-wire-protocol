use std::io::{Read, Write};
use std::net::TcpStream;

use kafka_wire_protocol::parser::{MetadataResponse, Parser, ParserError, ParserResult};

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
