package com.github.gustapinto.sales;

import com.github.gustapinto.sales.dto.CreateSalesRequest;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.Status.*;

@Path("/v1")
public class SalesResource {
    @Inject
    private SalesService salesService;

    @POST
    @Path("/sales")
    public Response create(CreateSalesRequest request) {
        final Sale sale = salesService.create(
            request.currency(),
            request.salesPersonCode(),
            request.totalAmount(),
            request.discountAmount());

        return Response.status(CREATED).entity(sale).build();
    }
}
