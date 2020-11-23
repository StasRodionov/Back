package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.services.interfaces.ContractorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContractorServiceImpl implements ContractorService {

    private final ContractorRepository contractorRepository;

    public ContractorServiceImpl(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }
}
