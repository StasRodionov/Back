package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.ReturnAmountByProduct;
import com.trade_accounting.models.dto.ReturnAmountByProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReturnAmountByProductMapper {

    ReturnAmountByProductDto toDto(ReturnAmountByProduct returnAmountByProduct);

    ReturnAmountByProduct toModel(ReturnAmountByProductDto returnAmountByProductDto);
    
}
