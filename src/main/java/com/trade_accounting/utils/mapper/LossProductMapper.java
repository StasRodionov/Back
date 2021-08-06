package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.LossProduct;
import com.trade_accounting.models.dto.LossProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LossProductMapper {

    @Mapping(source = "productId", target = "product.id")
    LossProduct toModel(LossProductDto lossProductDto);

    @Mapping(source = "product.id", target = "productId")
    LossProductDto toDto(LossProduct lossProduct);
}
