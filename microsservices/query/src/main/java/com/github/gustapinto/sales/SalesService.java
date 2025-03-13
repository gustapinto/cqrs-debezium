package com.github.gustapinto.sales;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.GetResponse;
import org.opensearch.client.opensearch.core.SearchResponse;

import com.github.gustapinto.sales.dto.GetSalesResponse;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SalesService {
    private static final String SALES_INDEX = "sales";

    @Inject
    private OpenSearchClient client;

    public List<GetSalesResponse> getAllSales() {
        try {
            final SearchResponse<GetSalesResponse> res = client.search(
                builder -> builder.index(SALES_INDEX),
                GetSalesResponse.class);
            final List<GetSalesResponse> sales = res.hits().hits().stream().map(hit -> hit.source()).toList();

            return sales;
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<GetSalesResponse> getSaleByCode(final UUID code) {
        try {
            final GetResponse<GetSalesResponse> res = client.get(
                builder -> builder.index(SALES_INDEX).id(code.toString()),
                GetSalesResponse.class);

            return Optional.ofNullable(res.source());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
