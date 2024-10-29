package com.contract_management.contract_and_supplier_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @Column(columnDefinition = "UUID", unique = true, nullable = false)
    private String id;
    private String name;
    private String address;
    private String phone;
    private String cnpj;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contract> contracts;

    public Supplier() {
        this.id = UUID.randomUUID().toString();
    }
}
