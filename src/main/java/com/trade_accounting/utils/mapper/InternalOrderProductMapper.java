package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.dto.InternalOrderProductsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InternalOrderProductMapper {
    /**
     * @return InternalOrderProducts
     */
    @Mapping(source = "productId", target = "product.id")
    InternalOrderProduct toModel(InternalOrderProductsDto internalOrderProductsDto);

    /**
     * @return InternalOrderProductsDto
     */
    @Mapping(source = "product.id", target = "productId")
    InternalOrderProductsDto toDto(InternalOrderProduct internalOrderProduct);
}
