package com.trade_accounting.utils.mapper.client;

import com.trade_accounting.models.dto.client.AccountDto;
import com.trade_accounting.models.entity.client.Account;
import com.trade_accounting.models.entity.client.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {


    Account toModel(AccountDto account);

    AccountDto toDto(Account account);

    default Employee idToEmployee(Long id) {
        return Employee.builder()
                .id(id)
                .build();
    }

    default Long employeeToId(Employee employee) {
        return employee.getId();
    }

}
