package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.dto.production.TechnicalOperationsDto;
import com.trade_accounting.models.entity.production.TechnicalOperations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicalOperationsMapper {
    //TechnicalOperations
    TechnicalOperations toModel(TechnicalOperationsDto technicalOperationsDto);

    @Mapping(target = "technicalCardId", source = "technicalCard.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "companyId", source = "company.id")
    TechnicalOperationsDto toDto(TechnicalOperations technicalOperations);
}
