package com.contract_management.contract_and_supplier_management.services.suppliers;

import com.contract_management.contract_and_supplier_management.controllers.suppliers.dtos.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.suppliers.dtos.SupplierResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.suppliers.dtos.SupplierUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier saveSupplier(SupplierRegisterDTO supplier);

    List<SupplierResponseDTO> getAllSuppliers();

    SupplierResponseDTO getSupplierById(String id);

    Supplier updateSupplier(SupplierUpdateDTO supplierToUpdate, String supplierId);

    void deleteSupplier(String supplierId);
}
