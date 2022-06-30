package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.dto.company.PriceListProductPercentsDto;
import com.trade_accounting.models.entity.company.PriceListProductPercents;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceListProductPercentsMapper {
    @Mapping(source = "priceListId", target = "priceList.id")
    PriceListProductPercents toModel(PriceListProductPercentsDto invoiceProductDto);

    @Mapping(source = "priceList.id", target = "priceListId")
    PriceListProductPercentsDto toDto(PriceListProductPercents invoiceProduct);
}
