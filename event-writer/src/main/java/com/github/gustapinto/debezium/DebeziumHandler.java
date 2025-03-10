package com.github.gustapinto.debezium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.gustapinto.debezium.dtos.DebeziumMessage;
import com.github.gustapinto.debezium.enums.DebeziumOperation;

public abstract class DebeziumHandler<T> {
    private final String errorFormat = "Operation not implemented in this handler [{}]";
    private final Logger logger = LoggerFactory.getLogger(DebeziumHandler.class);

    protected void create(final DebeziumMessage<T> message) {
        logger.warn(errorFormat, DebeziumOperation.CREATE);
    };

    protected void update(final DebeziumMessage<T> message) {
        logger.warn(errorFormat, DebeziumOperation.UPDATE);
    };

    protected void delete(final DebeziumMessage<T> message) {
        logger.warn(errorFormat, DebeziumOperation.DELETE);
    };

    protected void snapshot(final DebeziumMessage<T> message) {
        logger.warn(errorFormat, DebeziumOperation.SNAPSHOT);
    };

    protected void truncate(final DebeziumMessage<T> message) {
        logger.warn(errorFormat, DebeziumOperation.TRUNCATE);
    };

    protected void message(final DebeziumMessage<T> message) {
        logger.warn(errorFormat, DebeziumOperation.MESSAGE);
    };

    public void handle(final DebeziumMessage<T> message) {
        switch (message.getPayload().getOp()) {
            case CREATE -> this.create(message);
            case UPDATE -> this.update(message);
            case DELETE -> this.delete(message);
            case SNAPSHOT -> this.snapshot(message);
            case TRUNCATE -> this.truncate(message);
            case MESSAGE -> this.message(message);
        };
    }
}
