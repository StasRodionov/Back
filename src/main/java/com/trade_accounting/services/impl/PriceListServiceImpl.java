package com.trade_accounting.services.impl;

import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.repositories.PriceListRepository;
import com.trade_accounting.services.interfaces.PriceListService;
import com.trade_accounting.utils.mapper.PriceListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepository priceListRepository;
    private final PriceListMapper priceListMapper;

    @Override
    public List<PriceListDto> getAll() {
        return priceListRepository.findAll().stream()
                .map(priceListMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PriceListDto getById(Long id) {
        PriceList priceList = priceListRepository.getOne(id);
        return priceListMapper.toDto(priceList);
    }

    /**
     * @changed by Pavel Andrusov
     */
    @Override
    public PriceListDto create(PriceListDto dto) {
        PriceList priceList = priceListRepository.save(priceListMapper.toModel(dto));
        dto.setId(priceList.getId());
        return priceListMapper.toDto(priceList);
    }

    @Override
    public PriceListDto update(PriceListDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        priceListRepository.deleteById(id);
    }
}
