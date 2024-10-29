package com.contract_management.contract_and_supplier_management.services.suppliers;

import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierRegisterDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierResponseDTO;
import com.contract_management.contract_and_supplier_management.controllers.dtos.suppliers.SupplierUpdateDTO;
import com.contract_management.contract_and_supplier_management.models.Supplier;
import com.contract_management.contract_and_supplier_management.repositories.SupplierRepository;
import com.contract_management.contract_and_supplier_management.services.mappers.SupplierMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<SupplierResponseDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return SupplierMapper.fromSuppliers(suppliers);
    }

    @Transactional
    @Override
    public SupplierResponseDTO getSupplierById(String id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("supplier not found"));
        return SupplierMapper.fromSupplier(supplier);
    }

    @Transactional
    @Override
    public Supplier updateSupplier(SupplierUpdateDTO supplierToUpdate, String supplierId) {
        Supplier supplier = searchSupplierById(supplierId);
        updateSupplierFields(supplier, supplierToUpdate);
        return supplierRepository.save(supplier);
    }

    private Supplier searchSupplierById(String id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("supplier not found"));
    }

    private void updateSupplierFields(Supplier supplier, SupplierUpdateDTO supplierUpdateDTO) {
        if (!supplierUpdateDTO.getName().equals(supplier.getName())) {
            supplier.setName(supplierUpdateDTO.getName());
        }
        if (!supplierUpdateDTO.getAddress().equals(supplier.getAddress())) {
            supplier.setAddress(supplierUpdateDTO.getAddress());
        }
        if (!supplierUpdateDTO.getPhone().equals(supplier.getPhone())) {
            supplier.setPhone(supplierUpdateDTO.getPhone());
        }
        if (!supplierUpdateDTO.getCnpj().equals(supplier.getCnpj())) {
            supplier.setCnpj(supplierUpdateDTO.getCnpj());
        }
    }

    @Transactional
    @Override
    public void deleteSupplier(String supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
