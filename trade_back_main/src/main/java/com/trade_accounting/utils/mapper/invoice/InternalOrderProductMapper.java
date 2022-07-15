package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import com.trade_accounting.models.dto.invoice.InternalOrderProductsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InternalOrderProductMapper {
    //InternalOrderProduct
    @Mapping(source = "productId", target = "product.id")
    InternalOrderProduct toModel(InternalOrderProductsDto internalOrderProductsDto);

    @Mapping(target = "productId", source = "product.id")
    InternalOrderProductsDto toDto(InternalOrderProduct internalOrderProduct);
}