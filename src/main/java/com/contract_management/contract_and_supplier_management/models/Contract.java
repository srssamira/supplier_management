package com.contract_management.contract_and_supplier_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @Column(columnDefinition = "UUID", unique = true, nullable = false)
    private String id;
    private String numberContract;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalValue;
    private String description;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @JsonIgnore
    private Supplier supplier;

    public Contract() {
        this.id = UUID.randomUUID().toString();
    }
}
