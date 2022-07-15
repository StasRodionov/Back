package com.trade_accounting.utils.mapper.client;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.dto.client.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    //Department
    Department toModel(DepartmentDto department);

    DepartmentDto toDto(Department department);
}
