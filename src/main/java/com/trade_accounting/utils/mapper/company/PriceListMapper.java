package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.models.dto.company.PriceListDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceListMapper {
    //PriceList
    PriceList toModel(PriceListDto priceListDto);

    PriceListDto toDto(PriceList priceList);
}
