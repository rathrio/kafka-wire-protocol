use std::io::{Read, Write};
use std::net::TcpStream;

fn main() -> std::io::Result<()> {
    let mut stream = TcpStream::connect("127.0.0.1:9092")?;
    println!("Connected to server!");

    let request = build_metadata_request();
    stream.write_all(&request)?;
    println!("Sent metadata request");

    let mut buffer = [0; 4096];
    let bytes_read = stream.read(&mut buffer)?;
    if bytes_read > 0 {
        println!(
            "Received: {}",
            String::from_utf8_lossy(&buffer[..bytes_read])
        );
    } else {
        println!("Received: nothing!");
    }

    Ok(())
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
    let correlation_id: i32 = 2;
    let client_id_bytes = "rust-client".as_bytes();
    let client_id_length: i16 = client_id_bytes.len() as i16;
    let topics_count: i32 = 0; // Empty array to retrieve all topics
    let allow_auto_topic_creation: u8 = 0;
    let include_topic_authorized_operations: u8 = 0;

    let request_length: i32 = (2 + 2 + 4 + 2 + client_id_length + 4 + 2) as i32;

    dbg!(client_id_bytes);
    hex_dump(client_id_bytes);
    dbg!(request_length);

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

    hex_dump(&request);

    request
}
