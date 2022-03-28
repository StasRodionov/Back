package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.RequestsProductions;
import com.trade_accounting.models.dto.production.RequestsProductionsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestsProductionsMapper {
    //RequestsProductions
    RequestsProductions toModel(RequestsProductionsDto requestsProductionsDto);

    RequestsProductionsDto toDto(RequestsProductions requestsProductions);
}
