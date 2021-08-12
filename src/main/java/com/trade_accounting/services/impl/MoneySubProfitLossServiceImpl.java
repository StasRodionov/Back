package com.trade_accounting.services.impl;

import com.trade_accounting.models.MoneySubProfitLoss;
import com.trade_accounting.models.dto.MoneySubProfitLossDto;
import com.trade_accounting.repositories.MoneySubProfitLossRepository;
import com.trade_accounting.services.interfaces.MoneySubProfitLossService;
import com.trade_accounting.utils.DtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class MoneySubProfitLossServiceImpl implements MoneySubProfitLossService {

    private final MoneySubProfitLossRepository moneySubProfitLossRepository;
    private final MoneySubProfitLossMapper dtoMapper;

    public MoneySubProfitLossServiceImpl(MoneySubProfitLossRepository moneySubProfitLossRepository, DtoMapper dtoMapper) {
        this.moneySubProfitLossRepository = moneySubProfitLossRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public MoneySubProfitLossDto getById(Long id) {
        Optional<MoneySubProfitLoss> moneySubProfitLossById = moneySubProfitLossRepository.findById(id);
        return dtoMapper.moneySubProfitLossToDTO(moneySubProfitLossById.orElse(new MoneySubProfitLoss()));
    }

    @Override
    public List<MoneySubProfitLossDto> getAll() {
        return moneySubProfitLossRepository.getAll();
    }

    @Override
    public MoneySubProfitLossDto create(MoneySubProfitLossDto dto) {
        MoneySubProfitLoss moneySubProfitLoss = MoneySubProfitLoss.builder().id(dto.getId())
                .itemsList(dto.getItemsList())
                .profitLoss(dto.getProfitLoss())
                .build();
        return dtoMapper.moneySubProfitLossToDTO(moneySubProfitLossRepository.save(moneySubProfitLoss));
    }

    @Override
    public MoneySubProfitLossDto update(MoneySubProfitLossDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public List<MoneySubProfitLossDto> search(Specification<MoneySubProfitLoss> spec) {
        return executeSearch(moneySubProfitLossRepository, dtoMapper::moneySubProfitLossToDTO, spec);
    }
}