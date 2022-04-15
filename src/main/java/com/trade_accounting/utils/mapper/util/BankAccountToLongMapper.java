package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.company.BankAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountToLongMapper {

    default Long bankAccountToLong(BankAccount bankAccount) {
        return bankAccount.getId();
    }
}
