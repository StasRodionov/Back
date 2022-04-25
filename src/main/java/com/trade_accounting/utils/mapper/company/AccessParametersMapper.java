package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.AccessParameters;
import com.trade_accounting.models.dto.company.AccessParametersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccessParametersMapper {
    //AccessParameters
    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "departmentId", target = "department.id")
    AccessParameters toModel(AccessParametersDto accessParametersDto);

    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "departmentId", source = "department.id")
    AccessParametersDto toDto(AccessParameters accessParameters);
}
