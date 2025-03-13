package com.github.gustapinto.sales.dtos;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sale {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private UUID code;

    @JsonProperty("salesperson_code")
    private UUID salespersonCode;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("total_amount")
    private Double totalAmount;

    @JsonProperty("discount_amount")
    private Double discountAmount;

    @JsonProperty("final_amount")
    private Double finalAmount;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;
}
