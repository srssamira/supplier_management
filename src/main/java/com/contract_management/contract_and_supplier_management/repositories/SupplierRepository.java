package com.contract_management.contract_and_supplier_management.repositories;

import com.contract_management.contract_and_supplier_management.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
