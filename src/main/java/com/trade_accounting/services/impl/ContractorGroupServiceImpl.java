package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.services.interfaces.ContractorGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContractorGroupServiceImpl implements ContractorGroupService {

    private final ContractorGroupRepository contractorGroupRepository;

    public ContractorGroupServiceImpl(ContractorGroupRepository contractorGroupRepository) {
        this.contractorGroupRepository = contractorGroupRepository;
    }
}
