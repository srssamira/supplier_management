package com.contract_management.contract_and_supplier_management.services.mappers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.models.Supplier;

import java.util.ArrayList;
import java.util.List;

public class ContractMapper {

    public static Contract fromContractRegisterDTO(ContractRegisterDTO contractRegisterDTO) {
        Contract contract = new Contract();
        contract.setNumberContract(contractRegisterDTO.getNumberContract());
        contract.setDescription(contractRegisterDTO.getDescription());
        contract.setStartDate(contractRegisterDTO.getStartDate());
        contract.setEndDate(contractRegisterDTO.getEndDate());
        contract.setTotalValue(contractRegisterDTO.getTotalValue());

        return contract;
    }

    public static List<Contract> fromContractRegisterDTOList(List<ContractRegisterDTO> contractRegisterDTOList, Supplier supplier) {
        List<Contract> contracts = new ArrayList<>();
        for (ContractRegisterDTO contractRegisterDTO : contractRegisterDTOList) {
            Contract contract = new Contract();
            contract.setNumberContract(contractRegisterDTO.getNumberContract());
            contract.setDescription(contractRegisterDTO.getDescription());
            contract.setStartDate(contractRegisterDTO.getStartDate());
            contract.setEndDate(contractRegisterDTO.getEndDate());
            contract.setTotalValue(contractRegisterDTO.getTotalValue());
            contract.setSupplier(supplier);
            contracts.add(contract);
        }
        return contracts;
    }
}
