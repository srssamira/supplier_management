package com.contract_management.contract_and_supplier_management.controllers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;
import com.contract_management.contract_and_supplier_management.services.suppliers.SupplierService;
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
    public Supplier createSupplier(@RequestBody SupplierRegisterDTO supplierRegisterDTO) {
        return supplierService.saveSupplier(supplierRegisterDTO);
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping
    @RequestMapping("/{supplierId}")
    public Supplier getSupplierById(@PathVariable String supplierId) {
        return supplierService.getSupplierById(supplierId);
    }
}
