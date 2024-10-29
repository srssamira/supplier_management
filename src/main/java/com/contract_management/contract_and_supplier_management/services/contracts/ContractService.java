package com.contract_management.contract_and_supplier_management.services.contracts;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;

import java.time.LocalDate;
import java.util.List;

public interface ContractService {

    Contract saveContract(ContractRegisterDTO contractRegisterDTO, String supplierId);

    List<ContractResponseDTO> getAllContractsFromASupplier(String supplierId);

    ContractResponseDTO getContractById(String contractId);

    Contract updateContract(ContractUpdateDTO contractUpdateDTO, String contractId);

    void deleteContract(String contractId);

    List<ContractResponseDTO> getContractsByStartDate(String supplierId, LocalDate startDate);

    List<ContractResponseDTO> getContractsByEndDate(String supplierId, LocalDate endDate);

    List<ContractResponseDTO> getContractsByDescriptionContaining(String supplierId, String wordKey);

    List<ContractResponseDTO> getContractsByActivity(String supplierId, boolean activity);

}
