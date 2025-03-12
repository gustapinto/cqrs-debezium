package com.github.gustapinto.debezium.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.gustapinto.debezium.enums.DebeziumOperation;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumMessagePayload<T> {
    @JsonProperty("before")
    private T before;

    @JsonProperty("after")
    private T after;

    @JsonProperty("op")
    private DebeziumOperation op;

    @JsonProperty("ts_ms")
    private Long tsMs;
}
