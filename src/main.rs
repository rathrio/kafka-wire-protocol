use std::io::{Read, Write};
use std::net::TcpStream;

use kafka_wire_protocol::hex_dump;
use kafka_wire_protocol::protocol::{
    MetadataRequest, MetadataResponse, Parser, ParserError, ParserResult,
};

fn main() -> std::io::Result<()> {
    let mut stream = TcpStream::connect("127.0.0.1:9092")?;
    println!("Connected to server!");

    let request = MetadataRequest::new(23, "kafka-wire-protocol-client");
    stream.write_all(&request.encode()?)?;
    println!("Sent metadata request");

    let mut buffer = [0; 4096];
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
