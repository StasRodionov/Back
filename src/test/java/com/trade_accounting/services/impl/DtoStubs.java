package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.utils.DtoMapper;
import org.mapstruct.factory.Mappers;

public class DtoStubs {
    private static final DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

    public static TypeOfPriceDto getTypeOfPriceDto(Long id) {
        return dtoMapper.typeOfPriceToTypeOfPriceDto(ModelStubs.getTypeOfPrice(id));
    }
}
