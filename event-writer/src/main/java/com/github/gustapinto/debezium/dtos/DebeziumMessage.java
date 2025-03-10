package com.github.gustapinto.debezium.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumMessage<T> {
    @JsonProperty("payload")
    private DebeziumMessagePayload<T> payload;
}
