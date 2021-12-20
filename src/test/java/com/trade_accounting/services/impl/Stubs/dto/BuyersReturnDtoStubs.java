package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.BuyersReturnDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.BuyersReturnMapper;
import org.mapstruct.factory.Mappers;

public class BuyersReturnDtoStubs {
    private static final BuyersReturnMapper mapper = Mappers.getMapper(BuyersReturnMapper.class);
    public static BuyersReturnDto getBuyersReturnDto(Long id) {
        return mapper.toDto(ModelStubs.getBuyersReturn(id));
    }
}
