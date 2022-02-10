package com.trade_accounting.services.impl;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeDetailsServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getRepoFilledNotNull() {
        assertNotNull(employeeRepository);
    }

    @Test
    void loadUserByNameTest() {
        Optional<Employee> employeeFromRepo = Optional.empty();

        when(employeeRepository.findByEmail(anyString())).thenReturn(employeeFromRepo);

        EmployeeDto employee = employeeService.getByEmail("email@email.ru");

        assertEquals(new EmployeeDto(), employee, "failure - expected that employee was empty");
    }
}
