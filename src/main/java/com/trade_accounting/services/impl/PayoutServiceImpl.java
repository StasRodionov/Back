package com.trade_accounting.services.impl;

import com.trade_accounting.models.Payout;
import com.trade_accounting.models.dto.PayoutDto;
import com.trade_accounting.repositories.PayoutRepository;
import com.trade_accounting.services.interfaces.PayoutService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PayoutServiceImpl implements PayoutService {

    private final PayoutRepository payoutRepository;

    private final DtoMapper dtoMapper;

    public PayoutServiceImpl(PayoutRepository payoutRepository, DtoMapper dtoMapper) {
        this.payoutRepository = payoutRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<PayoutDto> getAll() {
        return payoutRepository.findAll().stream()
                .map(dtoMapper::payoutToPayoutDto)
                .collect(Collectors.toList());
    }

    @Override
    public PayoutDto getById(Long id) {
        return dtoMapper.payoutToPayoutDto(payoutRepository.getOne(id));
    }

    @Override
    public PayoutDto create(PayoutDto payoutDto) {
        Payout payout = payoutRepository.save(dtoMapper.payoutDtoToPayout(payoutDto));
        payout.setId(payoutDto.getId());
        return dtoMapper.payoutToPayoutDto(payout);
    }

    @Override
    public PayoutDto update(PayoutDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        payoutRepository.deleteById(id);
    }
}
