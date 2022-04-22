package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.ReturnAmountByProduct;
import com.trade_accounting.models.dto.finance.ReturnAmountByProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReturnAmountByProductMapper {
    //ReturnAmountByProduct

    ReturnAmountByProduct toModel(ReturnAmountByProductDto returnAmountByProductDto);


    ReturnAmountByProductDto toDto(ReturnAmountByProduct returnAmountByProduct);

}
