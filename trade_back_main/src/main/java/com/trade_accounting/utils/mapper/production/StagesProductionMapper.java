package com.trade_accounting.utils.mapper.production;


import com.trade_accounting.models.dto.production.StagesProductionDto;
import com.trade_accounting.models.entity.production.StagesProduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StagesProductionMapper {
    //StagesProduction

    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "employeeId", target = "employee.id")
    StagesProduction toModel(StagesProductionDto stagesProductionDto);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "employee.id", target = "employeeId")
    StagesProductionDto toDto(StagesProduction stagesProduction);
}