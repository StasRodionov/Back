package com.trade_accounting.services.impl.client;

import com.trade_accounting.models.dto.client.AccountDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.*;
import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.repositories.client.AccountRepository;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.client.PositionRepository;
import com.trade_accounting.services.interfaces.client.AccountService;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import com.trade_accounting.utils.mapper.client.AccountMapper;
import com.trade_accounting.utils.mapper.client.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final EmployeeService employeeService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

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
        return employee.getAccount();
    }

    @Override
    public List<EmployeeDto> getAll() {

        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(Long id) {
     Optional<Employee> employee = employeeRepository.findById(id);
        return employeeMapper.toDto(employee.orElse(new Employee()));
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        Employee employee = employeeMapper.toModel(dto);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto update(EmployeeDto empDto) {

      Employee emp = employeeMapper.toModel(empDto);
         return null;

    }

    @Override
    public void deleteById(Long id) {

    }
}
