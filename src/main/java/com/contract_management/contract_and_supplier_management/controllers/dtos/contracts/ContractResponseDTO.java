package com.contract_management.contract_and_supplier_management.controllers.dtos.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ContractResponseDTO {
    private String numberContract;
    private LocalDate startDate;
    private LocalDate endDate;
    private Number totalValue;
    private String description;
    private boolean active;
}
