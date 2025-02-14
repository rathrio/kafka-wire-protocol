use kafka_wire_protocol::protocol::MetadataRequest;
use kafka_wire_protocol::wire;

fn main() -> std::io::Result<()> {
    let request = MetadataRequest::new(23, "kafka-wire-protocol-client");
    let response = wire::submit_metadata_request(&request)?;
    println!("{:#?}", &response);

    Ok(())
}
