package com.trade_accounting.services.impl;

import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.repositories.PriceListRepository;
import com.trade_accounting.services.interfaces.PriceListService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        final List<PriceListDto> collect = priceListRepository.findAll().stream()
                .map(dtoMapper::priceListToPriceListDto)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public PriceListDto getById(Long id) {
        return dtoMapper.priceListToPriceListDto(priceListRepository.findById(id).orElse(new PriceList()));
    }

    @Override
    public PriceListDto create(PriceListDto dto) {
        PriceList priceList = priceListRepository.save(dtoMapper.priceListDtoToPriceList(dto));
        dto.setId(priceList.getId());
        return dtoMapper.priceListToPriceListDto(priceList);
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
