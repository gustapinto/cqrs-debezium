package com.github.gustapinto.sales.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateSalesRequest(
    @JsonProperty("currency")
    String currency,

    @JsonProperty("salesperson_code")
    UUID salesPersonCode,

    @JsonProperty("total_amount")
    Double totalAmount,

    @JsonProperty("discount_amount")
    Double discountAmount
) {
}
