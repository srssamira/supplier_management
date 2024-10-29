package com.contract_management.contract_and_supplier_management.services.contracts;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.models.Supplier;
import com.contract_management.contract_and_supplier_management.repositories.ContractRepository;
import com.contract_management.contract_and_supplier_management.repositories.SupplierRepository;
import com.contract_management.contract_and_supplier_management.services.mappers.ContractMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    @Override
    public Contract saveContract(ContractRegisterDTO contractRegisterDTO, Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("supplier not found"));
        Contract contract = ContractMapper.fromContractRegisterDTO(contractRegisterDTO);
        contract.setSupplier(supplier);

        return contractRepository.save(contract);
    }
}
