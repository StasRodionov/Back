package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDto> getAll();

    List<EmployeeDto> search(Specification<Employee> specification);

    EmployeeDto getById(Long id);

    void create(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto);

    void deleteById(Long id);

    EmployeeDto getByEmail(String email);
}
