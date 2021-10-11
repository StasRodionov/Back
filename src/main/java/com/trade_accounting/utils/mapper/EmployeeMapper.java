package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.InternalOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    // Employee
//    @Mappings({
//            @Mapping(source = "department", target = "departmentDto"),
//            @Mapping(source = "position", target = "positionDto"),
//            @Mapping(source = "roles", target = "roleDto"),
//            @Mapping(source = "image", target = "imageDto")
//    })
//    EmployeeDto toDto(Employee emp);

//    @Mappings({
//            @Mapping(source = "departmentDto", target = "department"),
//            @Mapping(source = "positionDto", target = "position"),
//            @Mapping(source = "roleDto", target = "roles"),
//            @Mapping(source = "imageDto", target = "image"),
//            @Mapping(target = "authorities", ignore = true)
//    })
//    Employee toModel(EmployeeDto emp);

    default Employee toModel(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }

        return Employee.builder()
                .id(employeeDto.getId())
                .lastName(employeeDto.getLastName())
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .sortNumber(employeeDto.getSortNumber())
                .phone(employeeDto.getPhone())
                .inn(employeeDto.getInn())
                .description(employeeDto.getDescription())
                .email(employeeDto.getEmail())
                .password(employeeDto.getPassword())
                .build();
    }

    default EmployeeDto toDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();
        if (employee == null) {
            return null;
        } else {

        employeeDto.setId(employee.getId());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setMiddleName(employee.getMiddleName());
        employeeDto.setSortNumber(employee.getSortNumber());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setInn(employee.getInn());
        employeeDto.setDescription(employee.getDescription());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPassword(employee.getPassword());

        employeeDto.setRoleDtoIds(
                employee.getRoles().stream()
                        .map(Role::getId)
                        .collect(Collectors.toSet())
        );

        if (employee.getDepartment() == null) {
            return null;
        } else {
            employeeDto.setDepartmentDtoId(employee.getDepartment().getId());
        }

        if (employee.getPosition() == null) {
            return null;
        } else {
            employeeDto.setPositionDtoId(employee.getPosition().getId());
        }

        if (employee.getImage() == null) {
            return null;
        } else {
            employeeDto.setImageDtoId(employee.getImage().getId());
        }

        return employeeDto;

        }
    }
}
