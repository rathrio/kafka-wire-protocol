use kafka_wire_protocol::protocol::{ApiVersionsRequest, MetadataRequest};
use kafka_wire_protocol::wire;

fn main() -> std::io::Result<()> {
    let request = ApiVersionsRequest::new(123, "rusty-kafka-client", "rusty-kafka-client", "0.0.1");
    wire::submit_api_versions_request(&request)?;

    // let request = MetadataRequest::new(23, "kafka-wire-protocol-client");
    // let response = wire::submit_metadata_request(&request)?;
    // println!("{:#?}", &response);

    Ok(())
}
