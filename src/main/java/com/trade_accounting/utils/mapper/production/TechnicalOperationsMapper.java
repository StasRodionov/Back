package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.dto.production.TechnicalOperationsDto;
import com.trade_accounting.models.entity.production.TechnicalOperations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicalOperationsMapper {
    //TechnicalOperations
    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    TechnicalOperations toModel(TechnicalOperationsDto technicalOperationsDto);

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "technicalCard", source = "technicalCard.id")
    @Mapping(target = "warehouse", source = "warehouse.id")
    @Mapping(target = "companyId", source = "company.id")
    TechnicalOperationsDto toDto(TechnicalOperations technicalOperations);
}
