package com.contract_management.contract_and_supplier_management.controllers.contracts.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ContractRegisterDTO {
    @NotNull(message = "number contract can't be null")
    @NotBlank(message = "number contract can't be blank")
    private String numberContract;

    @NotNull(message = "start date can't be null")
    private LocalDate startDate;

    @NotNull(message = "end date can't be null")
    private LocalDate endDate;

    @Min(value = 1)
    private Number totalValue;

    @NotNull(message = "description can't be null")
    @NotBlank(message = "description can't be blank")
    private String description;

    private boolean activity;

}
