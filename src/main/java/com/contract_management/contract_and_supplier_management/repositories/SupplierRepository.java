package com.contract_management.contract_and_supplier_management.repositories;

import com.contract_management.contract_and_supplier_management.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
