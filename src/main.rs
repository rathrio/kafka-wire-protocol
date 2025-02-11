use std::io::{Read, Write};
use std::net::TcpStream;

fn main() -> std::io::Result<()> {
    let mut stream = TcpStream::connect("127.0.0.1:9092")?;
    println!("Connected to server!");

    let message = b"Hello, server!";
    stream.write_all(message)?;
    println!("Sent: {:?}", String::from_utf8_lossy(message));

    let mut buffer = [0; 1024];
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
