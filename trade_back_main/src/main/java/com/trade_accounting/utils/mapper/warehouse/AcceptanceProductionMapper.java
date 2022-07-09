package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.AcceptanceProductionDto;
import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AcceptanceProductionMapper {
    //AcceptanceProduction
    @Mapping(source = "acceptanceId", target = "acceptance.id")
    AcceptanceProduction toModel(AcceptanceProductionDto acceptanceProductionDto);

    @Mapping(source = "acceptance.id", target = "acceptanceId")
    @Mapping(source = "product.id", target = "productId")
    AcceptanceProductionDto toDto(AcceptanceProduction acceptanceProduction);
}
