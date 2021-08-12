package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PayoutDto;
import com.trade_accounting.services.impl.Stubs.model.PayoutModelStubs;
import com.trade_accounting.utils.DtoMapper;
import org.mapstruct.factory.Mappers;

public class PayoutDtoStubs {
    private static final DtoMapper mapper = Mappers.getMapper(DtoMapper.class);

    public static PayoutDto getDto(Long id) {
        return mapper.payoutToPayoutDto(PayoutModelStubs.getPayout(id));
    }
}
