package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.dto.production.TechnicalCardProductionDto;
import com.trade_accounting.models.entity.production.TechnicalCardProduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicalCardProductionMapper {
    //TechnicalCardProduction

    TechnicalCardProduction toModel(TechnicalCardProductionDto technicalCardProductionDto);

    @Mapping(source = "product.id", target = "productId")
    TechnicalCardProductionDto toDto(TechnicalCardProduction technicalCardProduction);
}
