package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.TechnicalProcess;
import com.trade_accounting.models.dto.production.TechnicalProcessDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalProcessMapper {
    //TechnicalProcess
    TechnicalProcess toModel(TechnicalProcessDto dto);

    TechnicalProcessDto toDto(TechnicalProcess process);
}
