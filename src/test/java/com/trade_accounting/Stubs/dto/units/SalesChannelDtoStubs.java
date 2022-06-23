package com.trade_accounting.Stubs.dto.units;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.units.SalesChannelDto;
import com.trade_accounting.utils.mapper.units.SalesChannelMapper;
import org.mapstruct.factory.Mappers;

public class SalesChannelDtoStubs {

    private static final SalesChannelMapper mapper = Mappers.getMapper(SalesChannelMapper.class);
    public static SalesChannelDto getSalesChannelDto(Long id) {
        return mapper.toDto(
                ModelStubs.getSalesChannel(id));
    }
}
