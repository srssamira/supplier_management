package com.contract_management.contract_and_supplier_management.services.suppliers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierResponseDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;
import com.contract_management.contract_and_supplier_management.repositories.SupplierRepository;
import com.contract_management.contract_and_supplier_management.services.mappers.SupplierMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    @Override
    public Supplier saveSupplier(SupplierRegisterDTO supplierRegisterDTO) {
        return supplierRepository.save(SupplierMapper.fromSupplierRegisterDTO(supplierRegisterDTO));
    }

    @Transactional
    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Transactional
    @Override
    public Supplier getSupplierById(String id) {
        return supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("supplier not found"));
    }

}
