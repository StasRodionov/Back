package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.PageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface EmployeeService extends SearchableService<Employee, EmployeeDto>, AbstractService<EmployeeDto> {

    PageDto<EmployeeDto> search(Specification<Employee> specification, Pageable page);

    EmployeeDto getByEmail(String email);

    List<EmployeeDto> searchByString(String text);
}

