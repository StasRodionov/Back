package com.trade_accounting.services.impl.client;

import com.trade_accounting.models.dto.client.AccountDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.Account;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.repositories.client.AccountRepository;
import com.trade_accounting.services.interfaces.client.AccountService;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import com.trade_accounting.utils.mapper.client.AccountMapper;
import com.trade_accounting.utils.mapper.client.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final EmployeeService employeeService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final EmployeeMapper employeeMapper;

    @Override
    public AccountDto create(AccountDto account, EmployeeDto employee) {
        employeeService.create(employee);
        Account accountModel = accountMapper.toModel(account);
        accountModel.setEmployees(List.of(employeeMapper.toModel(employee)));
        accountRepository.save(accountModel);
        return accountMapper.toDto(accountModel);
    }

    @Override
    public Account getByEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return null;
    }

    @Override
    public EmployeeDto getById(Long id) {
        return null;
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        return null;
    }

    @Override
    public EmployeeDto update(EmployeeDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
