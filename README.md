# CQRS Debezium

A CQRS implementation using Debezium and Kafka as replication method

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://raw.githubusercontent.com/gustapinto/cqrs-debezium/main/docs/images/cqrs-dark.jpg">
  <source media="(prefers-color-scheme: light)" srcset="https://raw.githubusercontent.com/gustapinto/cqrs-debezium/main/docs/images/cqrs-light.jpg">
  <img src="https://raw.githubusercontent.com/gustapinto/cqrs-debezium/main/docs/images/cqrs-light.jpg">
</picture>

## Running the project

1. Clone the project and open it on a shell
2. Start the containers with `docker compose up`
3. For each microsservice, in a separate tab or shell do:
   1. `cd ./microsservices/<microsservice name>`
   2. `make start` or `./mvnw clean package && java -jar ./target/<microsservice name>.jar`

## Microsservices:

- **command:** The writer service, it uses a relational [PostgreSQL](https://www.postgresql.org/) database
- **event-writer:** The replication service, responsible from retrieving data from [Apache Kafka](https://kafka.apache.org/) and storing it on the [OpenSearch](https://opensearch.org/) read store
- **query:** The reader service, it uses a non relational [OpenSearch](https://opensearch.org/) store
