package com.contract_management.contract_and_supplier_management.controllers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.contracts.ContractUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.services.contracts.ContractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<ContractResponseDTO> getContracts(@PathVariable String supplierId) {
        return contractService.getAllContractsFromASupplier(supplierId);
    }

    @GetMapping
    @RequestMapping("/contracts/{contractId}")
    public ContractResponseDTO getContract(@PathVariable String contractId) {
        return contractService.getContractById(contractId);
    }

    @GetMapping
    @RequestMapping("/suppliers/{supplierId}/contractsByStartDate")
    public List<ContractResponseDTO> getContractsByStartDate(@PathVariable String supplierId, @RequestParam(required = true) LocalDate startDate) {
        return contractService.getContractsByStartDate(supplierId, startDate);
    }

    @GetMapping
    @RequestMapping("/suppliers/{supplierId}/contractsByEndDate")
    public List<ContractResponseDTO> getContractsByEndDate(@PathVariable String supplierId, @RequestParam(required = true) LocalDate endDate) {
        return contractService.getContractsByEndDate(supplierId, endDate);
    }

    @GetMapping
    @RequestMapping("/suppliers/{supplierId}/contractsByWordsInDescription")
    public List<ContractResponseDTO> getContractsByWordInDescription(@PathVariable String supplierId, @RequestParam(required = true) String wordKey) {
        return contractService.getContractsByDescriptionContaining(supplierId, wordKey);
    }

    @GetMapping
    @RequestMapping("/suppliers/{supplierId}/contractsByActivity")
    public List<ContractResponseDTO> getContractsByActivity(@PathVariable String supplierId, @RequestParam(required = true) boolean activity) {
        return contractService.getContractsByActivity(supplierId, activity);
    }


    @PutMapping
    @RequestMapping("/contracts/up/{contractId}")
    public Contract updateContract(@PathVariable String contractId, @RequestBody @Valid ContractUpdateDTO contractUpdateDTO) {
        return contractService.updateContract(contractUpdateDTO, contractId);
    }


    @DeleteMapping
    @RequestMapping("/contracts/del/{contractsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContract(@PathVariable String contractsId) {
        contractService.deleteContract(contractsId);
    }
}
