package com.github.gustapinto.sales;

import java.io.IOException;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.endpoints.BooleanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.gustapinto.debezium.DebeziumHandler;
import com.github.gustapinto.debezium.dtos.DebeziumMessage;
import com.github.gustapinto.sales.dtos.Sale;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
final class SalesHandler extends DebeziumHandler<Sale> {
    final Logger logger = LoggerFactory.getLogger(SalesHandler.class);
    final String index = "sales";

    @Inject
    OpenSearchClient client;

    @Override
    public void handle(final DebeziumMessage<Sale> message) throws Exception {
        createIndexIfNotExists(index);

        logger.info("Processing message {}", message);

        super.handle(message);
    }

    @Override
    protected void create(final DebeziumMessage<Sale> message) throws Exception {
        upsertSale(message.getPayload().getAfter());
    }

    @Override
    protected void update(final DebeziumMessage<Sale> message) throws Exception {
        this.create(message);
    }

    @Override
    protected void delete(final DebeziumMessage<Sale> message) throws Exception {
        final Sale sale = message.getPayload().getBefore();

        client.delete(builder -> builder.index(index).id(sale.getCode().toString()));
    }

    private void createIndexIfNotExists(final String index) throws IOException {
        final BooleanResponse res = client.indices().exists(builder -> builder.index(index));
        if (!res.value()) {
            client.indices().create(builder -> builder.index(index));
        }
    }

    private void upsertSale(final Sale sale) throws IOException {
        client.index(builder -> builder.index(index).id(sale.getCode().toString()).document(sale));
    }
}
