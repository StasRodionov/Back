package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PrepaymentReturnDto;
import com.trade_accounting.models.dto.PrepayoutDto;
import com.trade_accounting.services.impl.Stubs.model.PrepayoutModelStubs;
import com.trade_accounting.utils.mapper.PrepayoutMapper;
import org.mapstruct.factory.Mappers;

public class PrepayoutDtoStubs {
    private static final PrepayoutMapper mapper = Mappers.getMapper(PrepayoutMapper.class);

    public static PrepayoutDto getDto(Long id) {
        return mapper.toDto(PrepayoutModelStubs.getPrepayout(id));
    }
}
