package com.github.gustapinto.sales;

import java.time.Instant;
import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales")
public class Sale extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @Column(name = "code")
    public UUID code;

    @Column(name = "salesperson_code")
    public UUID salespersonCode;

    @Column(name = "currency", length = 10)
    public String currency;

    @Column(name = "total_amount")
    public Double totalAmount;

    @Column(name = "discount_amount")
    public Double discountAmount;

    @Column(name = "final_amount")
    public Double finalAmount;

    @Column(name = "created_at")
    public Instant createdAt;

    @Column(name = "updated_at")
    public Instant updatedAt;
}
