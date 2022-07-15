package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.dto.production.ProductionDto;
import com.trade_accounting.models.entity.production.Production;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductionMapper {
    //Production

    Production toModel(ProductionDto productionDto);


    @Mapping(source = "technicalCard.id", target = "technicalCardId")
    @Mapping(source = "requestsProductions.id", target = "requestsProductionsId")
    ProductionDto toDto(Production production);
}
