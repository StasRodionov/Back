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
            @Mapping(target = "technicalCard", ignore = true),
            @Mapping(target = "warehouse", ignore = true)
    })
    TechnicalOperations toModel(TechnicalOperationsDto technicalOperationsDto);

    @Mappings({
            @Mapping(target = "technicalCard", ignore = true),
            @Mapping(target = "warehouse", ignore = true)
    })
    TechnicalOperationsDto toDto(TechnicalOperations technicalOperations);
}
