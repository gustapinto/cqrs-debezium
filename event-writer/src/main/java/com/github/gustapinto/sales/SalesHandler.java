package com.github.gustapinto.sales;

import java.io.IOException;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.indices.CreateIndexRequest;
import org.opensearch.client.opensearch.indices.ExistsRequest;
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
    public void handle(final DebeziumMessage<Sale> message) {
        createIndexIfNotExists(index);

        logger.info("Processing Sale: {}", message.getPayload().getAfter());
        super.handle(message);
    }

    private void createIndexIfNotExists(final String index) {
        try {
            final ExistsRequest existsReq = new ExistsRequest.Builder().index(index).build();
            final BooleanResponse existsRes = client.indices().exists(existsReq);
            if (!existsRes.value()) {
                final CreateIndexRequest request = new CreateIndexRequest.Builder().index(index).build();
                client.indices().create(request);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void create(final DebeziumMessage<Sale> message) {
        final Sale sale = message.getPayload().getAfter();
        final IndexRequest<Sale> request = new IndexRequest.Builder<Sale>()
            .index(index)
            .id(sale.getCode())
            .document(sale)
            .build();

        try {
            client.index(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void update(final DebeziumMessage<Sale> message) {
        // TODO
        super.update(message);
    }

    @Override
    protected void delete(final DebeziumMessage<Sale> message) {
        // TODO
        super.delete(message);
    }
}
