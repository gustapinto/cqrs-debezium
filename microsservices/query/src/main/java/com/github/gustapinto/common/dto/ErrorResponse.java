package com.github.gustapinto.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_NULL)
public record ErrorResponse(
    @JsonProperty("error")
    String error,

    @JsonProperty("error_class")
    String errorClass
) {
}
