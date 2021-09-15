package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.MutualSettlementsDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.MutualSettlementsMapper;
import org.mapstruct.factory.Mappers;

public class MutualSettlementsDtoStubs {
    private static final MutualSettlementsMapper mapper = Mappers.getMapper(MutualSettlementsMapper.class);
    public static MutualSettlementsDto getMutualSettlementsDto(Long id) {
        return mapper.toDto(ModelStubs.getMutualSettlements(id));
    }
}
