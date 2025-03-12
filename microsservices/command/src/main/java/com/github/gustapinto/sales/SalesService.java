package com.github.gustapinto.sales;

import java.time.Instant;
import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;

@RequestScoped
public class SalesService {
    private static final Double MINIMUM_FINAL_AMOUNT = 5.0;

    @Transactional
    public Sale create(
        final String currency,
        final UUID salesPersonCode,
        final Double totalAmount,
        final Double discountAmount
    ) {
        final Sale sale = new Sale();
        sale.code = UUID.randomUUID();
        sale.salespersonCode = salesPersonCode;
        sale.currency = formatCurrency(currency);
        sale.totalAmount = totalAmount;
        sale.discountAmount = discountAmount;
        sale.finalAmount = calculateFinalAmount(totalAmount, discountAmount);
        sale.createdAt = Instant.now();

        sale.persist();

        return sale;
    }

    private String formatCurrency(final String currency) {
        if (currency == null) {
            return "BRL";
        }

        return currency.trim().toUpperCase();
    }

    private Double calculateFinalAmount(final Double totalAmount, final Double discountAmount) {
        if (totalAmount == null || discountAmount == null) {
            return MINIMUM_FINAL_AMOUNT;
        }

        final Double amount = totalAmount - discountAmount;
        if (amount <= MINIMUM_FINAL_AMOUNT ) {
            return MINIMUM_FINAL_AMOUNT;
        }

        return amount;
    }
}
