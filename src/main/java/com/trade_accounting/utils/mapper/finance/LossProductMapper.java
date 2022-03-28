package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.LossProduct;
import com.trade_accounting.models.dto.finance.LossProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LossProductMapper {
    //LossProduct
    LossProduct toModel(LossProductDto lossProductDto);

    LossProductDto toDto(LossProduct lossProduct);
}
