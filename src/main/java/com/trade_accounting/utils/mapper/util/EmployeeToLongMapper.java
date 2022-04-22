package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.client.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeToLongMapper {
    default Long toLong(Employee employee) {
        return employee.getId();
    }
}
