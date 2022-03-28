package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import com.trade_accounting.models.dto.finance.PrepaymentReturnDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrepaymentReturnMapper {
    //PrepaymentReturn
    PrepaymentReturn toModel(PrepaymentReturnDto prepaymentReturnDto);

    PrepaymentReturnDto toDto(PrepaymentReturn prepaymentReturn);
}
