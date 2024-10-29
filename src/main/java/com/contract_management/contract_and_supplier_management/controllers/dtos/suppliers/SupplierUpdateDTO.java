package com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@NoArgsConstructor
public class SupplierUpdateDTO {
    @NotNull
    @NotBlank
    @JsonIgnore
    private String id;

    @NotNull(message = "name can't be null")
    @NotBlank(message = "name can't be blank")
    String name;

    @NotNull(message = "cnpj can't be null")
    @NotBlank(message = "cnpj can't be blank")
    @CNPJ(message = "cnpj outside the brazilian standard format")
    String cnpj;

    @NotNull(message = "cnpj can't be null")
    @NotBlank(message = "cnpj can't be blank")
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}-[0-9]{4}$", message = "phone outside the brazilian standard formato")
    String phone;

    @NotNull(message = "adress can't be null")
    @NotBlank(message = "adress can't be blank")
    String address;
}
