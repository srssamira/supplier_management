package com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplierResponseDTO {
    private String name;
    private String cnpj;
    private String phone;
    private String adress;
}
