package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.dto.PriceListDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceListMapper {
    //PriceList
    PriceListDto toDto(PriceList priceList);

    PriceList toModel(PriceListDto priceListDto);
}
