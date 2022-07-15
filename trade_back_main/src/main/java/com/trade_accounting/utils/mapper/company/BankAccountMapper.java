package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.dto.company.BankAccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    //BankAccount
    BankAccount bankAccountDtoToBankAccount(BankAccountDto bankAccountDto);

    BankAccountDto bankAccountToBankAccountDto(BankAccount bankAccount);
}
