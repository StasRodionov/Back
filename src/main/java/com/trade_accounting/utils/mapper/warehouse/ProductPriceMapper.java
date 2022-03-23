package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.models.dto.warehouse.ProductPriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {
    //ProductPrice
    ProductPrice toModel(ProductPriceDto productPriceDto);

    ProductPriceDto toDto(ProductPrice productPrice);
}
