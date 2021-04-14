package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.dto.PaymentDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAll();

    PaymentDto getById(Long id);

    PaymentDto create(PaymentDto paymentDto);

    PaymentDto update(PaymentDto paymentDto);

    void deleteById(Long id);

    List<PaymentDto> search(Specification<Payment> specification);
}
