package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.StagesProduction;
import com.trade_accounting.models.entity.production.TechnicalProcess;
import com.trade_accounting.models.dto.production.TechnicalProcessDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicalProcessMapper {
    //TechnicalProcess
    @Mapping(target = "dateOfChanged", source = "dateOfChanged", dateFormat = "yyyy-MM-dd HH:mm:ss")
    TechnicalProcess toModel(TechnicalProcessDto dto);

    @Mapping(target = "stagesProductionSetIds", source = "stagesProductionSet")
    @Mapping(target = "departmentOwnerId", source = "departmentOwner.id")
    @Mapping(target = "employeeOwnerId", source = "employeeOwner.id")
    @Mapping(target = "dateOfChanged", source = "dateOfChanged", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "employeeWhoLastChangedId", source = "employeeWhoLastChanged.id")
    TechnicalProcessDto toDto(TechnicalProcess process);

    default Long stagesProductionToLong(StagesProduction stagesProduction) {
        return stagesProduction.getId();
    }

}
