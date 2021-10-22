package com.trade_accounting.utils.mapper;


import com.trade_accounting.models.StagesProduction;
import com.trade_accounting.models.dto.StagesProductionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StagesProductionMapper {

     @Mappings({
             @Mapping(source = "department.id", target = "departmentId"),
             @Mapping(source = "employee.id", target = "employeeId")
     })
     StagesProductionDto toDto(StagesProduction stagesProduction);

     @Mappings({
             @Mapping(source = "departmentId", target = "department.id"),
             @Mapping(source = "employeeId", target = "employee.id")
     })
     StagesProduction toModel(StagesProductionDto stagesProductionDto);
}