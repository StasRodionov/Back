package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.repositories.PriceListRepository;
import com.trade_accounting.services.interfaces.PriceListService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriceListServiceImpl implements PriceListService {


    private final PriceListRepository priceListRepository;
    private final DtoMapper dtoMapper;

    public PriceListServiceImpl(PriceListRepository priceListRepository, DtoMapper dtoMapper) {
        this.priceListRepository = priceListRepository;
        this.dtoMapper = dtoMapper;
    }


    @Override
    public List<PriceListDto> getAll() {
        return null;
    }

    @Override
    public PriceListDto getById(Long id) {
        return null;
    }

    @Override
    public PriceListDto create(PriceListDto dto) {
        return null;
    }

    @Override
    public PriceListDto update(PriceListDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
