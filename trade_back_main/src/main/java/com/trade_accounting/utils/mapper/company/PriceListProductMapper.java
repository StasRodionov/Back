package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.dto.company.PriceListProductDto;
import com.trade_accounting.models.entity.company.PriceListProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceListProductMapper {
    @Mapping(source = "priceListId", target = "priceList.id")
    @Mapping(source = "productId", target = "products.id")
    PriceListProduct toModel(PriceListProductDto invoiceProductDto);

    @Mapping(source = "priceList.id", target = "priceListId")
    @Mapping(source = "products.id", target = "productId")
    PriceListProductDto toDto(PriceListProduct invoiceProduct);
}
