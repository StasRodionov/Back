package com.trade_accounting.services.interfaces.client;


import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.Account;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.services.interfaces.util.AbstractService;

import java.util.List;


public interface AccountService {
     List<EmployeeDto> getAll();
    EmployeeDto getById(Long id);
    Account createAccount(EmployeeDto employeeDto);
    Employee createEmployee(EmployeeDto employeeDto);
    Employee update(EmployeeDto empDto);
    void deleteAccountById(Long id);

}

