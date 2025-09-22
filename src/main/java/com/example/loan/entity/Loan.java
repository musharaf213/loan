package com.example.loan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(500)
    @Max(12000)
    private Double amount;

    @NotNull
    private String customerName;

    @NotNull
    private String customerId;

    //Getter, Setter, Konstruktor

    public Loan(Long id, Double amount, String customerName, String customerId) {
        this.id = id;
        this.amount = amount;
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // Automatische Generierung der customerId bei einem neuen Post-Request
    @PrePersist
    public void generateCustomerId() {
        if (this.customerId == null || this.customerId.isEmpty()) {
            this.customerId = UUID.randomUUID().toString();
        }

    }

}