package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Production;
import com.trade_accounting.models.dto.ProductionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductionMapper {
    //Production
    ProductionDto toDto(Production production);

    Production toModel(ProductionDto productionDto);
}
