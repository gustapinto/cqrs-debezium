package com.github.gustapinto.sales;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gustapinto.common.dto.ErrorResponse;
import com.github.gustapinto.sales.dto.GetSalesResponse;
import jakarta.ws.rs.core.Response.Status;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1")
public class SalesResource {
    @Inject
    private SalesService salesService;

    @GET
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSales() {
        try {
            final List<GetSalesResponse> sales = salesService.getAllSales();

            return Response.status(Status.OK).entity(sales).build();
        } catch (RuntimeException e) {
            final ErrorResponse error = new ErrorResponse(e.getMessage(), e.getClass().getName());

            return Response.status(422).entity(error).build();
        }
    }

    @GET
    @Path("/sales/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSaleByCode(final UUID code) {
        try {
            final Optional<GetSalesResponse> sale = salesService.getSaleByCode(code);

            if (sale.isEmpty()) {
                return Response.status(Status.NOT_FOUND).build();
            }

            return Response.status(Status.OK).entity(sale.get()).build();
        } catch (RuntimeException e) {
            final ErrorResponse error = new ErrorResponse(e.getMessage(), e.getClass().getName());

            return Response.status(422).entity(error).build();
        }
    }
}
