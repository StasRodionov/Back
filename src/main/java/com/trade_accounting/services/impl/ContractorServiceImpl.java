package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.services.interfaces.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContractorServiceImpl implements ContractorService {

    private ContractorRepository contractorRepository;

    @Autowired
    public void setContractorRepository(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }
}
