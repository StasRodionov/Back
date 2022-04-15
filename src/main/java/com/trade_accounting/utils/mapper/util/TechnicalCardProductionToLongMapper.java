package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.production.TechnicalCardProduction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalCardProductionToLongMapper {

    default Long toLong(TechnicalCardProduction technicalCardProduction) {
        return technicalCardProduction.getId();
    }
}
