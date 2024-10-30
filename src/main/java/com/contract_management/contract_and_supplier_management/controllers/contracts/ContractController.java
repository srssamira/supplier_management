package com.contract_management.contract_and_supplier_management.controllers.contracts;

import com.contract_management.contract_and_supplier_management.controllers.contracts.dtos.ContractRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.contracts.dtos.ContractResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.contracts.dtos.ContractUpdateDTO;
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



    @PostMapping("/suppliers/{supplierId}/contracts")
    @ResponseStatus(HttpStatus.CREATED)
    public Contract createContract(@PathVariable String supplierId, @RequestBody @Valid ContractRegisterDTO contractRegisterDTO) {
        return contractService.saveContract(contractRegisterDTO, supplierId);
    }



    @GetMapping("/suppliers/{supplierId}/contracts")
    public List<ContractResponseDTO> getContracts(@PathVariable String supplierId) {
        return contractService.getAllContractsFromASupplier(supplierId);
    }

    @GetMapping("/contracts/{contractId}")
    public ContractResponseDTO getContract(@PathVariable String contractId) {
        return contractService.getContractById(contractId);
    }

    @GetMapping("/suppliers/{supplierId}/contractsByStartDate")
    public List<ContractResponseDTO> getContractsByStartDate(@PathVariable String supplierId, @RequestParam LocalDate startDate) {
        return contractService.getContractsByStartDate(supplierId, startDate);
    }

    @GetMapping("/suppliers/{supplierId}/contractsByEndDate")
    public List<ContractResponseDTO> getContractsByEndDate(@PathVariable String supplierId, @RequestParam LocalDate endDate) {
        return contractService.getContractsByEndDate(supplierId, endDate);
    }

    @GetMapping("/suppliers/{supplierId}/contractsByWordKey")
    public List<ContractResponseDTO> getContractsByWordInDescription(@PathVariable String supplierId, @RequestParam String wordKey) {
        return contractService.getContractsByDescriptionContaining(supplierId, wordKey);
    }

    @GetMapping("/suppliers/{supplierId}/contractsByActivity")
    public List<ContractResponseDTO> getContractsByActivity(@PathVariable String supplierId, @RequestParam boolean activity) {
        return contractService.getContractsByActivity(supplierId, activity);
    }



    @PutMapping("/contracts/{contractId}")
    public Contract updateContract(@PathVariable String contractId, @RequestBody @Valid ContractUpdateDTO contractUpdateDTO) {
        return contractService.updateContract(contractUpdateDTO, contractId);
    }



    @DeleteMapping("/contracts/{contractsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContract(@PathVariable String contractsId) {
        contractService.deleteContract(contractsId);
    }
}
