kafka/setup:
	mkdir -p kafka_data && sudo chown -R 1001:1001 kafka_data

debezium/setup:
	curl -H 'Content-Type: application/json' localhost:8083/connectors --data '{ "name": "postgres-connector-1", "config": { "connector.class": "io.debezium.connector.postgresql.PostgresConnector", "plugin.name": "pgoutput", "database.hostname": "db", "database.port": "5432", "database.user": "cqrs-db-user", "database.password": "cqrs-db-passw", "database.dbname": "cqrs-db", "database.server.name": "postgres", "topic.prefix" : "dbz", "schema.include.list": "public", "decimal.handling.mode": "double", "value.converter.schemas.enable": false } }'

setup: kafka/setup debezium/setup
