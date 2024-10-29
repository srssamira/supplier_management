package com.contract_management.contract_and_supplier_management.services.mappers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;

public class SupplierMapper {

    public static Supplier fromSupplierRegisterDTO(SupplierRegisterDTO supplierRegisterDTO) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierRegisterDTO.getName());
        supplier.setAddress(supplierRegisterDTO.getAddress());
        supplier.setPhone(supplierRegisterDTO.getPhone());
        supplier.setCnpj(supplierRegisterDTO.getCnpj());

        return supplier;
    }

}
