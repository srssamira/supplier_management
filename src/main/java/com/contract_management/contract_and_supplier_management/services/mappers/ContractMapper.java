package com.contract_management.contract_and_supplier_management.services.mappers;

import com.contract_management.contract_and_supplier_management.controllers.contracts.dtos.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.contracts.dtos.ContractResponseDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.services.contracts.ContractServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ContractMapper {

    public static Contract fromContractRegisterDTO(ContractRegisterDTO contractRegisterDTO) {
        Contract contract = new Contract();
        contract.setNumberContract(contractRegisterDTO.getNumberContract());
        contract.setDescription(contractRegisterDTO.getDescription());
        contract.setStartDate(contractRegisterDTO.getStartDate());
        contract.setEndDate(contractRegisterDTO.getEndDate());
        contract.setTotalValue(new BigDecimal(contractRegisterDTO.getTotalValue().toString()));
        ContractServiceImpl.checkEndDate(contractRegisterDTO.getEndDate(), contractRegisterDTO.getStartDate());

        return contract;
    }

    public static ContractResponseDTO fromContract(Contract contract) {
        ContractResponseDTO contractResponseDTO = new ContractResponseDTO();
        contractResponseDTO.setNumberContract(contract.getNumberContract());
        contractResponseDTO.setDescription(contract.getDescription());
        contractResponseDTO.setStartDate(contract.getStartDate());
        contractResponseDTO.setEndDate(contract.getEndDate());
        contractResponseDTO.setTotalValue(new BigDecimal(contract.getTotalValue().toString()));
        contractResponseDTO.setActive(contract.isActivity());
        return contractResponseDTO;
    }

    public static List<ContractResponseDTO> fromContracts(List<Contract> contracts) {
        List<ContractResponseDTO> contractResponseDTOS = new ArrayList<>();
        for (Contract contract : contracts) {
            contractResponseDTOS.add(fromContract(contract));
        }
        return contractResponseDTOS;
    }

}
