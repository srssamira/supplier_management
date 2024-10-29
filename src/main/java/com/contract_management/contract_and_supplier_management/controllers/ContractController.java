package com.contract_management.contract_and_supplier_management.controllers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.services.contracts.ContractService;
import com.contract_management.contract_and_supplier_management.services.mappers.ContractMapper;
import com.contract_management.contract_and_supplier_management.services.suppliers.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    @RequestMapping("/suppliers/{supplierId}/contracts")
    @ResponseStatus(HttpStatus.CREATED)
    public Contract createContract(@PathVariable Long supplierId, @RequestBody ContractRegisterDTO contractRegisterDTO) {
        return contractService.saveContract(contractRegisterDTO, supplierId);
    }
}
