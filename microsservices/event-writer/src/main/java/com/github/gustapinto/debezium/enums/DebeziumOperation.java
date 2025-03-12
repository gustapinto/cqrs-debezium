package com.github.gustapinto.debezium.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum DebeziumOperation {
    @JsonProperty("c")
    CREATE,

    @JsonProperty("u")
    UPDATE,

    @JsonProperty("d")
    DELETE,

    @JsonProperty("r")
    SNAPSHOT,

    @JsonProperty("t")
    TRUNCATE,

    @JsonProperty("m")
    MESSAGE,
}
