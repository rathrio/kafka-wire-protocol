pub mod protocol;

pub fn hex_dump(bytes: &[u8]) {
    for chunk in bytes.chunks(16) {
        print!("{:08x}: ", chunk.as_ptr() as usize);
        for byte in chunk {
            print!("{:02x} ", byte);
        }
        println!();
    }
}
