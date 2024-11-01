package com.contract_management.contract_and_supplier_management.controllers.suppliers;

import com.contract_management.contract_and_supplier_management.controllers.suppliers.dtos.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.suppliers.dtos.SupplierResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.suppliers.dtos.SupplierUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;
import com.contract_management.contract_and_supplier_management.services.suppliers.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier createSupplier(@RequestBody @Valid SupplierRegisterDTO supplierRegisterDTO) {
        return supplierService.saveSupplier(supplierRegisterDTO);
    }



    @GetMapping
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{supplierId}")
    public SupplierResponseDTO getSupplierById(@PathVariable String supplierId) {
        return supplierService.getSupplierById(supplierId);
    }



    @PutMapping("/{supplierId}")
    public Supplier updateSupplier(@PathVariable String supplierId, @RequestBody @Valid SupplierUpdateDTO supplierUpdateDTO) {
        return supplierService.updateSupplier(supplierUpdateDTO, supplierId);
    }



    @DeleteMapping("/{supplierId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(@PathVariable String supplierId) {
        supplierService.deleteSupplier(supplierId);
    }
}
