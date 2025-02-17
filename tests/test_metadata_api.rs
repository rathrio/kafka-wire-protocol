use kafka_wire_protocol::{
    protocol::{ApiVersionsRequest, MetadataRequest},
    wire::Wire,
};
use testcontainers::{
    core::{IntoContainerPort, WaitFor},
    runners::SyncRunner,
    Container, GenericImage, ImageExt,
};

fn setup_kafka_container() -> Container<GenericImage> {
    GenericImage::new("confluentinc/cp-kafka", "7.8.1")
        .with_wait_for(WaitFor::message_on_stdout("Kafka startTime"))
        .with_exposed_port(9092.tcp())
        .with_mapped_port(9099, 9092.tcp())
        .with_network("bridge")
        .with_env_var("DEBUG", "1")
        .with_env_var("KAFKA_KRAFT_MODE", "true")
        .with_env_var("KAFKA_NODE_ID", "1")
        .with_env_var("KAFKA_PROCESS_ROLES", "controller,broker")
        .with_env_var("KAFKA_CONTROLLER_QUORUM_VOTERS", "1@localhost:9093")
        .with_env_var(
            "KAFKA_LISTENERS",
            "PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093",
        )
        .with_env_var(
            "KAFKA_LISTENER_SECURITY_PROTOCOL_MAP",
            "PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT",
        )
        .with_env_var("KAFKA_INTER_BROKER_LISTENER_NAME", "PLAINTEXT")
        .with_env_var("KAFKA_CONTROLLER_LISTENER_NAMES", "CONTROLLER")
        .with_env_var("KAFKA_ADVERTISED_LISTENERS", "PLAINTEXT://localhost:9092")
        .with_env_var("KAFKA_LOG_DIRS", "/var/lib/kafka/data")
        .with_env_var("KAFKA_AUTO_CREATE_TOPICS_ENABLE", "true")
        .with_env_var("KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR", "1")
        .with_env_var("KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS", "0")
        .with_env_var("CLUSTER_ID", "kafka-wire-protocol-test-cluster-9000")
        .start()
        .expect("Failed to start Kafka")
}

#[test]
#[ignore = "integration"]
fn test_metadata_api() {
    let container = setup_kafka_container();

    let mut wire = Wire::new("localhost", 9099).expect("Failed to connect to Kafka");
    let request = MetadataRequest::new(1234, "test-client");
    let _response = wire.submit_metadata_request(&request).unwrap();

    container.stop().expect("Failed to stop Kafka");
}

#[test]
#[ignore = "integration"]
fn test_api_versions_api() {
    let container = setup_kafka_container();

    let mut wire = Wire::new("localhost", 9099).expect("Failed to connect to Kafka");
    let request = ApiVersionsRequest::new(42, "asdf", "asdf name", "0.0.1");
    let _response = wire.submit_api_versions_request(&request).unwrap();

    container.stop().expect("Failed to stop Kafka");
}
