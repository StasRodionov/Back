package com.trade_accounting.services.impl;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.services.interfaces.PaymentService;
import com.trade_accounting.utils.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    @Override
    public List<PaymentDto> getAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentMapper.toDto(
                paymentRepository.findById(id).orElse(new Payment())
        );
    }

    @Override
    public PaymentDto create(PaymentDto paymentDto) {
        Payment payment = paymentRepository.save(paymentMapper.toModel(paymentDto));
        paymentDto.setId(payment.getId());
        return paymentDto;
    }


    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        return create(paymentDto);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentDto> search(String search) {
        return paymentRepository.search(search).stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> filter(Specification<Payment> specification) {
        return paymentRepository.findAll(specification).stream().
                map(paymentMapper::toDto).collect(Collectors.toList());
    }
}
