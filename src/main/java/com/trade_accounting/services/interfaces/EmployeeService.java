package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAll();

    List<EmployeeDto> search(Specification<Employee> specification);

    EmployeeDto getById(Long id);

    void save(EmployeeDto employeeDto);

    void deleteById(Long id);

    EmployeeDto getByEmail(String email);

    List<EmployeeDto> findBySearch(String search);

}
