use std::io::{Read, Write};
use std::net::TcpStream;

use kafka_wire_protocol::protocol::{MetadataRequest, Parser};

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

        if let (Ok(response), bytes_consumed) = Parser::parse(&bytes) {
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
