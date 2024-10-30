package com.contract_management.contract_and_supplier_management.services.contracts;

import com.contract_management.contract_and_supplier_management.controllers.contracts.dtos.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.contracts.dtos.ContractResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.contracts.dtos.ContractUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.models.Supplier;
import com.contract_management.contract_and_supplier_management.repositories.ContractRepository;
import com.contract_management.contract_and_supplier_management.repositories.SupplierRepository;
import com.contract_management.contract_and_supplier_management.services.mappers.ContractMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Contract> contracts = supplierRepository.findById(supplierId).orElseThrow(() -> new RuntimeException("supplier not found")).getContracts();
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
        if (!contract.getNumberContract().equals(contractUpdateDTO.getNumberContract()))
            contract.setNumberContract(contractUpdateDTO.getNumberContract());

        if (!contract.getDescription().equals(contractUpdateDTO.getDescription()))
            contract.setDescription(contractUpdateDTO.getDescription());

        if (!contract.getStartDate().equals(contractUpdateDTO.getStartDate()))
            contract.setStartDate(contractUpdateDTO.getStartDate());

        if (!contract.getEndDate().equals(contractUpdateDTO.getEndDate()))
            contract.setEndDate(contractUpdateDTO.getEndDate());

        if (!contract.getTotalValue().equals(contractUpdateDTO.getTotalValue()))
            contract.setTotalValue(new BigDecimal(contractUpdateDTO.getTotalValue().toString()));

        if (contract.isActivity() != contractUpdateDTO.isActivity())
            contract.setActivity(checkEndDate(contractUpdateDTO.getEndDate(), contractUpdateDTO.getStartDate()));
    }

    @Transactional
    @Override
    public void deleteContract(String contractId) {
        contractRepository.deleteById(contractId);
    }

    public static boolean checkEndDate(LocalDate endDate, LocalDate startDate) {
        LocalDate now = LocalDate.now();
        if (endDate.isAfter(now))
            return true;

        if (endDate.isBefore(startDate))
            throw new RuntimeException("end date is before start date");
        return false;
    }


    @Transactional
    @Override
    public List<ContractResponseDTO> getContractsByStartDate(String supplierId, LocalDate startDate) {
        return ContractMapper.fromContracts(contractRepository.findBySupplierIdAndStartDate(supplierId, startDate));
    }

    @Transactional
    @Override
    public List<ContractResponseDTO> getContractsByEndDate(String supplierId, LocalDate endDate) {
        return ContractMapper.fromContracts(contractRepository.findBySupplierIdAndEndDate(supplierId, endDate));
    }

    @Transactional
    @Override
    public List<ContractResponseDTO> getContractsByDescriptionContaining(String supplierId, String wordKey) {
        return ContractMapper.fromContracts(contractRepository.findBySupplierIdAndDescriptionContaining(supplierId, wordKey));
    }

    @Transactional
    @Override
    public List<ContractResponseDTO> getContractsByActivity(String supplierId, boolean activity) {
        List<Contract> contracts = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("supplier not found")).getContracts();

        return contracts.stream()
                .map(ContractMapper::fromContract)
                .filter(contractResponseDTO -> contractResponseDTO.isActive() == activity)
                .collect(Collectors.toList());
    }
}
