package com.trade_accounting.services.impl;

import com.trade_accounting.models.Production;
import com.trade_accounting.models.dto.ProductionDto;
import com.trade_accounting.repositories.ProductionRepository;
import com.trade_accounting.services.interfaces.ProductionService;
import com.trade_accounting.utils.mapper.ProductionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;

    private final ProductionMapper productionMapper;

    @Override
    public List<ProductionDto> getAll() {
        final List<ProductionDto> collect = productionRepository.findAll().stream()
                .map(productionMapper::toDto)
                .collect(Collectors.toList());
        return collect;
    }


    @Override
    public ProductionDto getById(Long id) {
        return productionMapper.toDto(productionRepository.findById(id).orElse(new Production()));
    }

    @Override
    public ProductionDto create(ProductionDto dto) {
        Production production = productionRepository.save(productionMapper.toModel(dto));
        dto.setId(production.getId());
        return productionMapper.toDto(production);
    }

    @Override
    public ProductionDto update(ProductionDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        productionRepository.deleteById(id);
    }

}
