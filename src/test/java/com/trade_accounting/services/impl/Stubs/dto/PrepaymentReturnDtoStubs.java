package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PrepaymentReturnDto;
import com.trade_accounting.services.impl.Stubs.model.PrepaymentReturnModelStubs;
import com.trade_accounting.utils.mapper.PrepaymentReturnMapper;
import org.mapstruct.factory.Mappers;

public class PrepaymentReturnDtoStubs {

    private static final PrepaymentReturnMapper mapper = Mappers.getMapper(PrepaymentReturnMapper.class);

    public static PrepaymentReturnDto getDto(Long id) {
        return mapper.toDto(PrepaymentReturnModelStubs.getPrepaymentReturn(id));
    }
}
