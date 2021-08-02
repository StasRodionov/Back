package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.dto.ProductPriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {
    //ProductPrice
    @Mappings({
            @Mapping(source = "typeOfPrice", target = "typeOfPriceDto")
    })
    ProductPriceDto toDto(ProductPrice productPrice);

    @Mappings({
            @Mapping(source = "typeOfPriceDto", target = "typeOfPrice")
    })
    ProductPrice toModel(ProductPriceDto productPriceDto);
}
