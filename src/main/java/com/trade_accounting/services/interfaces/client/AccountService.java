package com.trade_accounting.services.interfaces.client;

import com.trade_accounting.models.dto.client.AccountDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.Account;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.services.interfaces.util.AbstractService;


public interface AccountService extends AbstractService<EmployeeDto> {

    AccountDto create(AccountDto account, EmployeeDto employee);

    Account getByEmployee(Employee employee);

}

