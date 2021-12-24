package com.trade_accounting.services.impl;

import com.trade_accounting.models.PurchaseHistoryOfSales;
import com.trade_accounting.models.dto.PurchaseHistoryOfSalesDto;
import com.trade_accounting.repositories.PurchaseHistoryOfSalesRepository;
import com.trade_accounting.services.interfaces.PurchaseHistoryOfSalesService;
import com.trade_accounting.utils.mapper.PurchaseHistoryOfSalesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseHistoryOfSalesServiceImpl implements PurchaseHistoryOfSalesService {
    private final PurchaseHistoryOfSalesRepository purchaseHistoryOfSalesRepository;
    private final PurchaseHistoryOfSalesMapper purchaseHistoryOfSalesMapper;

    @Override
    public List<PurchaseHistoryOfSalesDto> getAll() {
        return purchaseHistoryOfSalesRepository.findAll().stream()
                .map(purchaseHistoryOfSalesMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseHistoryOfSalesDto getById(Long id) {
        return purchaseHistoryOfSalesMapper.toDto(purchaseHistoryOfSalesRepository.getOne(id));
    }

    @Override
    public PurchaseHistoryOfSalesDto create(PurchaseHistoryOfSalesDto dto) {
        PurchaseHistoryOfSales purchaseHistoryOfSales = purchaseHistoryOfSalesMapper.toModel(dto);
        purchaseHistoryOfSalesRepository.save(purchaseHistoryOfSales);
        return dto;
    }

    @Override
    public PurchaseHistoryOfSalesDto update(PurchaseHistoryOfSalesDto dto) {
        PurchaseHistoryOfSales purchaseHistoryOfSales = purchaseHistoryOfSalesMapper.toModel(dto);
        purchaseHistoryOfSalesRepository.save(purchaseHistoryOfSales);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        purchaseHistoryOfSalesRepository.deleteById(id);
    }
}
