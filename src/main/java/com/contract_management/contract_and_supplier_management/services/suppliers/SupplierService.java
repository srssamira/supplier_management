package com.contract_management.contract_and_supplier_management.services.suppliers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;

public interface SupplierService {
    Supplier saveSupplier(SupplierRegisterDTO supplier);
}
