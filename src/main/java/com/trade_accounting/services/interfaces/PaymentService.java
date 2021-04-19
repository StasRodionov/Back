package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.PaymentDto;

import java.util.List;

public interface PaymentService extends AbstractService<PaymentDto> {

    List<PaymentDto> search(String search);
}
