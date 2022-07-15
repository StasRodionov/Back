package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.RequestsProductions;
import com.trade_accounting.models.dto.production.RequestsProductionsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestsProductionsMapper {
    //RequestsProductions
    @Mapping(target = "dateOfTheCertificate", source = "dateOfTheCertificate", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "technicalCardId", target = "technicalCard.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    RequestsProductions toModel(RequestsProductionsDto requestsProductionsDto);

    @Mapping(target = "dateOfTheCertificate", source = "dateOfTheCertificate", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "technicalCardId", source = "technicalCard.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    RequestsProductionsDto toDto(RequestsProductions requestsProductions);
}
