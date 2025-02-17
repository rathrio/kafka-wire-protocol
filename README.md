# Kafka Wire Protocol

Experimenting with a Rust implementation of the [Kafka Wire Protocol](https://kafka.apache.org/protocol).

## Running tests

```sh
cargo test
```

As the integration tests are a bit slow, they are ignored by default, include
them with:

```sh
cargo test -- --include-ignored
```
