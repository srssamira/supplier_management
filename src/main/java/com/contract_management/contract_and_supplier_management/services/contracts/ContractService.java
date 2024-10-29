package com.contract_management.contract_and_supplier_management.services.contracts;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;

public interface ContractService {

    Contract saveContract(ContractRegisterDTO contractRegisterDTO, Long supplierId);

}
