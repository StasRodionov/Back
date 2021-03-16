package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.utils.DtoMapper;
import org.mapstruct.factory.Mappers;

public class DtoStubs {
    public static DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

    public static PaymentDto getPaymentDto(Long id) {
        return dtoMapper.paymentToPaymentDto(ModelStubs.getPayment(id));
    }
}
