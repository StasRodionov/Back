package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.LossProduct;
import com.trade_accounting.models.dto.finance.LossProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LossProductMapper {
    //LossProduct
//    @Mapping(source = "productId", target = "product.id")
    LossProduct toModel(LossProductDto lossProductDto);

    @Mapping(target = "productId", source = "product.id")
    LossProductDto toDto(LossProduct lossProduct);
}
