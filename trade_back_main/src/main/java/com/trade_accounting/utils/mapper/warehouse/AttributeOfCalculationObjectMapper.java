package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.AttributeOfCalculationObject;
import com.trade_accounting.models.dto.warehouse.AttributeOfCalculationObjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeOfCalculationObjectMapper {
    //AttributeOfCalculationObjectDto
    AttributeOfCalculationObject toModel(AttributeOfCalculationObjectDto attributeOfCalculationObjectDto);

    AttributeOfCalculationObjectDto toDto(AttributeOfCalculationObject attributeOfCalculationObject);
}
