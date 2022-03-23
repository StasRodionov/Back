package com.trade_accounting.utils.mapper.client;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.client.Role;
import com.trade_accounting.models.dto.client.EmployeeDto;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    //Employee
    Employee toModel(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);
}
