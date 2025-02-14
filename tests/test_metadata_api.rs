use kafka_wire_protocol::{protocol::MetadataRequest, wire};
use testcontainers::{
    core::{IntoContainerPort, WaitFor},
    runners::SyncRunner,
    GenericImage, ImageExt,
};

#[test]
#[ignore = "integration"]
fn test_metadata_api() {
    let _container = GenericImage::new("confluentinc/cp-kafka", "7.8.1")
        .with_wait_for(WaitFor::message_on_stdout("Kafka startTime"))
        .with_exposed_port(9092.tcp())
        .with_mapped_port(9092, 9092.tcp())
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
        .expect("Failed to start Kafka");

    let request = MetadataRequest::new(1234, "test-client");
    let response = wire::submit_metadata_request(&request).unwrap();
}
