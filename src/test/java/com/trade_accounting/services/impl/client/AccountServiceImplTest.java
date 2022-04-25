package com.trade_accounting.services.impl.client;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.client.AccountDtoStubs;
import com.trade_accounting.Stubs.dto.client.DepartmentDtoStubs;
import com.trade_accounting.Stubs.dto.client.EmployeeDtoStubs;
import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.Account;
import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.repositories.client.AccountRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import com.trade_accounting.utils.mapper.client.AccountMapper;
import com.trade_accounting.utils.mapper.client.EmployeeMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private AccountMapper accountMapper;

    @Spy
    private EmployeeMapperImpl employeeMapper;

    @InjectMocks
    private  AccountServiceImpl accountService;


    @Test
    void create_shouldPassInstructionsSuccessfulCreate(){

        when(employeeService.create(EmployeeDtoStubs.getEmployeeDto(1L)))
                .thenReturn( EmployeeDto.builder().build());

        when(accountMapper.toModel(AccountDtoStubs.getAccountDto(1L)))
                .thenReturn(Account.builder().build());

        accountService.create(AccountDtoStubs.getAccountDto(1L),EmployeeDtoStubs.getEmployeeDto(1L));

        verify(accountRepository).save(any(Account.class));
        log.info("account was save repository");
    }

    @Test
    void getAll_shouldReturnListFilledEmployeeDto(){

        when(employeeRepository.findAll())
                .thenReturn(
                        Stream.of(
                                        ModelStubs.getEmployee(1L),
                                        ModelStubs.getEmployee(2L),
                                        ModelStubs.getEmployee(3L)
                                )
                                .collect(Collectors.toList())
                );

        List<EmployeeDto> employeeDtoList = accountService.getAll();

        assertNotNull(employeeDtoList, "Failure - expected that list of employeeDto not null");
        assertTrue(employeeDtoList.size() > 0, "failure - expected that size of list of employeeDto greater than 0");

        for (EmployeeDto employeeDto : employeeDtoList) {
              employeeDtoIsCorrectlyInited(employeeDto);
        }

    }

    @Test
    void getAll_shouldReturnEmptyListEmployeeDto() {
        when(employeeRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<EmployeeDto> employees = accountService.getAll();

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertEquals(0, employees.size(), "failure - expected that size of list of employeeDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledEmployeeDto() {
        Optional<Employee> employeeRepo = Optional.of(ModelStubs.getEmployee(1L));

        when(employeeRepository.findById(anyLong()))
                .thenReturn(employeeRepo);

        EmployeeDto employeeDto = accountService.getById(1L);

        employeeDtoIsCorrectlyInited(employeeDto);
        log.info("employee get from repository");
    }
    @Test
    void create_shouldPassInstructionsSuccessfulCreateEmployee(){
        accountService.create(EmployeeDtoStubs.getEmployeeDto(1L));

        verify(employeeRepository).save(any(Employee.class));
        log.info("was success creat");

    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate(){
        accountService.update(EmployeeDtoStubs.getEmployeeDto(1L));

        verify(employeeRepository).save(any(Employee.class));
        log.info("was succesfully update");
    }


    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        accountService.deleteById(1L);

        verify(accountRepository).deleteById(1L);
        log.info("was delete from repository");
    }


    void employeeDtoIsCorrectlyInited(EmployeeDto employeeDto) {
        assertNotNull(employeeDto, "failure - fail in passed employeeDto");
        assertNotNull(employeeDto.getId(), "failure - fail in field 'id' into employeeDto");
        assertNotNull(employeeDto.getEmail(), "failure - fail in field 'email' into employeeDto");
        assertNotNull(employeeDto.getSortNumber(), "failure - fail in field 'sortNumber' into employeeDto");
    }


}
