package com.contract_management.contract_and_supplier_management.controllers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.services.contracts.ContractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractController {

    @Autowired
    private ContractService contractService;


    @PostMapping
    @RequestMapping("/suppliers/{supplierId}/contracts")
    @ResponseStatus(HttpStatus.CREATED)
    public Contract createContract(@PathVariable String supplierId, @RequestBody @Valid ContractRegisterDTO contractRegisterDTO) {
        return contractService.saveContract(contractRegisterDTO, supplierId);
    }

    @GetMapping
    @RequestMapping("/suppliers/{supplierId}/allContracts")
    public List<Contract> getContracts(@PathVariable String supplierId) {
        return contractService.getAllContractsFromASupplier(supplierId);
    }

    @GetMapping
    @RequestMapping("/contracts/{contractId}")
    public Contract getContract(@PathVariable String contractId) {
        return contractService.getContractById(contractId);
    }

    @PutMapping
    @RequestMapping("/contracts/up/{contractId}")
    public Contract updateContract(@PathVariable String contractId, @RequestBody ContractUpdateDTO contractUpdateDTO) {
        return contractService.updateContract(contractUpdateDTO, contractId);
    }
}
