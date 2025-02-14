# Kafka Wire Protocol

Experimenting with a Rust implementation of the [Kafka Wire Protocol](https://kafka.apache.org/protocol).

## Running integration tests

Integration tests don't run by default with `cargo test`. Run them with:

```sh
cargo test -- --ignored
```
