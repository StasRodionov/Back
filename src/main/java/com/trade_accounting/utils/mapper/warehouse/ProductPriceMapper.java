package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.ProductPriceDto;
import com.trade_accounting.models.entity.warehouse.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {
    //ProductPrice
    ProductPrice toModel(ProductPriceDto productPriceDto);

    @Mapping(target = "typeOfPriceId", source = "typeOfPrice.id")
    ProductPriceDto toDto(ProductPrice productPrice);

}
