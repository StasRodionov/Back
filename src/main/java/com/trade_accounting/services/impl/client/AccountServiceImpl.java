package com.trade_accounting.services.impl.client;

import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.*;
import com.trade_accounting.repositories.client.AccountRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.services.interfaces.client.AccountService;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import com.trade_accounting.utils.mapper.client.AccountMapper;
import com.trade_accounting.utils.mapper.client.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;


    @Override
    public Account createAccount(EmployeeDto employeeDto) {
        Account account = new Account();
        account.setEmployees(List.of(employeeMapper.toModel(employeeDto)));
        return  accountRepository.save(account);

    }

    @Override
    public Employee updateEmployee(EmployeeDto employeeDto) {
        Account account = new Account();
        account.setEmployees(List.of(employeeMapper.toModel(employeeDto)));
        accountRepository.save(account);
        return employeeRepository.save(employeeMapper.toModel(employeeDto));

    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
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

//    @Override
//    public Employee update(EmployeeDto empDto) {
//      return createEmployee(empDto);
//    }

}
