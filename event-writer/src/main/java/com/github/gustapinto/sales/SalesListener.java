package com.github.gustapinto.sales;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.github.gustapinto.sales.dtos.SaleMessage;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SalesListener {
    @Inject
    private SalesHandler handler;

    @Incoming("sales-in")
    public void listen(final SaleMessage message) {
        handler.handle(message);
    }
}
