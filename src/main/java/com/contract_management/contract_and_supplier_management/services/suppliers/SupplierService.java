package com.contract_management.contract_and_supplier_management.services.suppliers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier saveSupplier(SupplierRegisterDTO supplier);

    List<Supplier> getAllSuppliers();

    Supplier getSupplierById(String id);

    Supplier updateSupplier(SupplierUpdateDTO supplierToUpdate, String supplierId);
}
