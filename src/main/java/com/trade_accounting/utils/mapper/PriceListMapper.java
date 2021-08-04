package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.dto.PriceListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PriceListMapper {
    //PriceList
    @Mappings({
            @Mapping(source = "company.id", target = "company_id")
    })
    PriceListDto toDto(PriceList priceList);

        @Mappings({
            @Mapping(source = "company_id", target = "company.id")
    })
    PriceList toModel(PriceListDto priceListDto);
}
