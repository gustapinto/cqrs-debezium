package com.github.gustapinto.sales.dto;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_NULL)
public record GetSalesResponse(
    @JsonProperty("id")
    Long id,

    @JsonProperty("code")
    UUID code,

    @JsonProperty("salesperson_code")
    UUID salespersonCode,

    @JsonProperty("currency")
    String currency,

    @JsonProperty("total_amount")
    Double totalAmount,

    @JsonProperty("discount_amount")
    Double discountAmount,

    @JsonProperty("final_amount")
    Double finalAmount,

    @JsonProperty("created_at")
    Instant createdAt,

    @JsonProperty("updated_at")
    Instant updatedAt
) {
}
