package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.dto.PriceListDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceListMapper {

    @Mapping(source = "company.id", target = "companyId")
    PriceListDto toDto(PriceList priceList);

    @InheritInverseConfiguration
    PriceList toModel(PriceListDto priceListDto);
}
