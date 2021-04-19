package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EmployeeService extends AbstractService<EmployeeDto> {

    List<EmployeeDto> search(Specification<Employee> specification);

    EmployeeDto getByEmail(String email);

    void save(EmployeeDto employeeDto);

}
