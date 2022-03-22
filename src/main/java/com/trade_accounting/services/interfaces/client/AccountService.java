package com.trade_accounting.services.interfaces.client;

import com.trade_accounting.models.dto.client.AccountDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.dto.util.PageDto;
import com.trade_accounting.models.entity.client.Account;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface AccountService extends AbstractService<EmployeeDto> {

    AccountDto create(AccountDto account, EmployeeDto employee);

    Account getByEmployee(Employee employee);


}

