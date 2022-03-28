package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.TechnicalOperations;
import com.trade_accounting.models.dto.production.TechnicalOperationsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TechnicalOperationsMapper {
    //TechnicalOperations
    @Mappings({
            @Mapping(source = "technicalCardId", target = "technicalCard.id"),
            @Mapping(source = "warehouseId", target = "warehouse.id")
    })
    TechnicalOperations toModel(TechnicalOperationsDto technicalOperationsDto);

    @Mappings({
            @Mapping(source = "technicalCard.id", target = "technicalCardId"),
            @Mapping(source = "warehouse.id", target = "warehouseId")
    })
    TechnicalOperationsDto toDto(TechnicalOperations technicalOperations);
}
