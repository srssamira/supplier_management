package com.contract_management.contract_and_supplier_management.repositories;

import com.contract_management.contract_and_supplier_management.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

    List<Contract> findBySupplierIdAndStartDate(String supplierId, LocalDate startDate);

    List<Contract> findBySupplierIdAndEndDate(String supplierId, LocalDate endDate);

    List<Contract> findBySupplierIdAndDescriptionContaining(String supplierId, String word);

}
