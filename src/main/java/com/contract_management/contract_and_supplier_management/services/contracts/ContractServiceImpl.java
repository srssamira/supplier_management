package com.contract_management.contract_and_supplier_management.services.contracts;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.models.Supplier;
import com.contract_management.contract_and_supplier_management.repositories.ContractRepository;
import com.contract_management.contract_and_supplier_management.repositories.SupplierRepository;
import com.contract_management.contract_and_supplier_management.services.mappers.ContractMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    @Override
    public Contract saveContract(ContractRegisterDTO contractRegisterDTO, String supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("supplier not found"));
        Contract contract = ContractMapper.fromContractRegisterDTO(contractRegisterDTO);
        contract.setSupplier(supplier);

        return contractRepository.save(contract);
    }

    @Transactional
    @Override
    public List<ContractResponseDTO> getAllContractsFromASupplier(String supplierId) {
        List<Contract> contracts = supplierRepository.findById(supplierId).get().getContracts();
        return ContractMapper.fromContracts(contracts);
    }

    @Transactional
    @Override
    public ContractResponseDTO getContractById(String contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new RuntimeException("contract not found"));
        return ContractMapper.fromContract(contract);

    }

    @Transactional
    @Override
    public Contract updateContract(ContractUpdateDTO contractUpdateDTO, String contractId) {
        Contract contract = searchContractById(contractId);
        updateContractFields(contract, contractUpdateDTO);
        return contractRepository.save(contract);
    }

    private Contract searchContractById(String contractId) {
        return contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("contract not found"));
    }

    private void updateContractFields(Contract contract, ContractUpdateDTO contractUpdateDTO) {
        if (!contract.getNumberContract().equals(contractUpdateDTO.getNumberContract())) {
            contract.setNumberContract(contractUpdateDTO.getNumberContract());
        }

        if (!contract.getDescription().equals(contractUpdateDTO.getDescription())) {
            contract.setDescription(contractUpdateDTO.getDescription());
        }

        if (!contract.getStartDate().equals(contractUpdateDTO.getStartDate())) {
            contract.setStartDate(contractUpdateDTO.getStartDate());
        }

        if (!contract.getEndDate().equals(contractUpdateDTO.getEndDate())) {
            contract.setEndDate(contractUpdateDTO.getEndDate());
        }

        if (contract.getTotalValue() != contractUpdateDTO.getTotalValue()) {
            contract.setTotalValue(new BigDecimal(contractUpdateDTO.getTotalValue().toString()));
        }
    }

    @Transactional
    @Override
    public void deleteContract(String contractId) {
        contractRepository.deleteById(contractId);
    }
}
