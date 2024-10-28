package com.contract_management.contract_and_supplier_management.repositories;

import com.contract_management.contract_and_supplier_management.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
