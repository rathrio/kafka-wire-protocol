use std::io::{Read, Write};
use std::net::TcpStream;

use crate::protocol::{
    ApiVersionsRequest, ApiVersionsResponse, MetadataRequest, MetadataResponse, Parser,
};

pub struct Wire {
    stream: TcpStream,
}

// TODO: cleanup duplicate code
impl Wire {
    pub fn new(host: &str, port: u16) -> std::io::Result<Self> {
        let stream = TcpStream::connect(format!("{}:{}", host, port))?;
        println!("Connected to server!");
        Ok(Self { stream })
    }

    pub fn submit_metadata_request(
        &mut self,
        request: &MetadataRequest,
    ) -> std::io::Result<MetadataResponse> {
        self.stream.write_all(&request.encode()?)?;
        println!("Sent metadata request");

        let mut buffer = [0; 4096];
        let mut bytes = Vec::new();
        loop {
            let bytes_read = self.stream.read(&mut buffer)?;
            // End of stream.
            if bytes_read == 0 {
                break;
            }
            bytes.extend_from_slice(&buffer[..bytes_read]);

            if let Ok((response, bytes_consumed)) = Parser::parse_metadata_response(&bytes) {
                bytes.drain(..bytes_consumed);
                println!("Received complete response");
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

    pub fn submit_api_versions_request(
        &mut self,
        request: &ApiVersionsRequest,
    ) -> std::io::Result<ApiVersionsResponse> {
        self.stream.write_all(&request.encode()?)?;
        println!("Sent metadata request");

        let mut buffer = [0; 4096];
        let mut bytes = Vec::new();
        loop {
            let bytes_read = self.stream.read(&mut buffer)?;
            // End of stream.
            if bytes_read == 0 {
                break;
            }
            bytes.extend_from_slice(&buffer[..bytes_read]);

            if let Ok((response, bytes_consumed)) = Parser::parse_api_versions_response(&bytes) {
                bytes.drain(..bytes_consumed);
                println!("Received complete response");
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
}
