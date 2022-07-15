package com.trade_accounting.Stubs.dto.client;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.client.AccountDto;
import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.utils.mapper.client.AccountMapper;
import com.trade_accounting.utils.mapper.client.DepartmentMapper;
import org.mapstruct.factory.Mappers;

public class AccountDtoStubs {
    private static final AccountMapper mapper = Mappers.getMapper(AccountMapper.class);
    public static AccountDto getAccountDto(Long id) {
        return mapper.toDto(
                ModelStubs.getAccount(id));
    }
}
