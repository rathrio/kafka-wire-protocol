use std::io::{Read, Write};
use std::net::TcpStream;

use crate::protocol::{MetadataRequest, MetadataResponse, Parser};

// temporary helper. I should introduce some persistent connection mechanism per broker
pub fn submit_metadata_request(request: &MetadataRequest) -> std::io::Result<MetadataResponse> {
    let mut stream = TcpStream::connect("localhost:9092")?;
    println!("Connected to server!");

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

        if let (Ok(response), bytes_consumed) = Parser::parse(&bytes) {
            bytes.drain(..bytes_consumed);
            println!("Received complete response");
            println!("{:#?}", &response);
            return Ok(response);
        } else {
            println!("Not enough bytes")
        }
    }

    Err(std::io::Error::new(
        std::io::ErrorKind::Other,
        "Failed to receive complete message",
    ))
}
