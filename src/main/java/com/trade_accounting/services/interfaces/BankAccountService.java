package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.dto.BankAccountDto;

import java.util.List;

public interface BankAccountService extends AbstractService<BankAccountDto>   {
    List<String> getBankUniqueBic();
}

