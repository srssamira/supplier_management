package com.contract_management.contract_and_supplier_management.services.contracts;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;

import java.util.List;

public interface ContractService {

    Contract saveContract(ContractRegisterDTO contractRegisterDTO, String supplierId);

    List<Contract> getAllContractsFromASupplier(String supplierId);

    Contract getContractById(String contractId);

    Contract updateContract(ContractUpdateDTO contractUpdateDTO, String contractId);
}
