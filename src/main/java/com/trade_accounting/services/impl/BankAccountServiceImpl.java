package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.services.interfaces.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;

    @Autowired
    public void setBankAccountRepository(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
}
