package com.contract_management.contract_and_supplier_management.services.infra;

import com.contract_management.contract_and_supplier_management.models.Contract;
import com.contract_management.contract_and_supplier_management.repositories.ContractRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ContractActivityModerator {

    @Autowired
    private ContractRepository contractRepository;

    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void updateContractActivity() {

            LocalDate now = LocalDate.now();

            List<Contract> inactiveContractrs = contractRepository.findByEndDateBefore(now);
            for (Contract contract : inactiveContractrs) {
                contract.setActivity(false);
                contractRepository.save(contract);
            }

    }
}
