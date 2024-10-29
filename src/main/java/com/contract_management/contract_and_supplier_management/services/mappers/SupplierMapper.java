package com.contract_management.contract_and_supplier_management.services.mappers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierMapper {

    public static Supplier fromSupplierRegisterDTO(SupplierRegisterDTO supplierRegisterDTO) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierRegisterDTO.getName());
        supplier.setAddress(supplierRegisterDTO.getAddress());
        supplier.setPhone(supplierRegisterDTO.getPhone());
        supplier.setCnpj(supplierRegisterDTO.getCnpj());

        return supplier;
    }

    public static SupplierResponseDTO fromSupplier(Supplier supplier) {
        SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();
        supplierResponseDTO.setName(supplier.getName());
        supplierResponseDTO.setPhone(supplier.getPhone());
        supplierResponseDTO.setCnpj(supplier.getCnpj());
        supplierResponseDTO.setAdress(supplier.getAddress());
        return supplierResponseDTO;
    }

    public static List<SupplierResponseDTO> fromSuppliers(List<Supplier> suppliers) {
        List<SupplierResponseDTO> supplierResponseDTOs = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierResponseDTOs.add(fromSupplier(supplier));
        }
        return supplierResponseDTOs;
    }
}
