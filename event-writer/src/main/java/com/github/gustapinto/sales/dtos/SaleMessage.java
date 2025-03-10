package com.github.gustapinto.sales.dtos;

import com.github.gustapinto.debezium.dtos.DebeziumMessage;

// Just a wrapper class to allow automatic Jackson JSON Deserialization
public class SaleMessage extends DebeziumMessage<Sale> {
}
