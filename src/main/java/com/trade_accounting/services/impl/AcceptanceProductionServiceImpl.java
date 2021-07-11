package com.trade_accounting.services.impl;

import com.trade_accounting.models.AcceptanceProduction;
import com.trade_accounting.models.dto.AcceptanceProductionDto;
import com.trade_accounting.repositories.AcceptanceProductionRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.AcceptanceProductionService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptanceProductionServiceImpl implements AcceptanceProductionService {

    private final AcceptanceProductionRepository acceptanceProductionRepository;

    private final ProductRepository productRepository;

    private final DtoMapper dtoMapper;

    public AcceptanceProductionServiceImpl(AcceptanceProductionRepository acceptanceProductionRepository, ProductRepository productRepository, DtoMapper dtoMapper) {
        this.acceptanceProductionRepository = acceptanceProductionRepository;
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
    }


    @Override
    public List<AcceptanceProductionDto> getAll() {
        return acceptanceProductionRepository.findAll().stream()
                .map(dtoMapper::toAcceptanceProductionDto)
                .collect(Collectors.toList());
    }

    @Override
    public AcceptanceProductionDto getById(Long id) {
        return dtoMapper.toAcceptanceProductionDto(
                //acceptanceProductionRepository.findById(id).orElse(new AcceptanceProduction())
                acceptanceProductionRepository.getOne(id)
        );
    }

    @Override
    public AcceptanceProductionDto create(AcceptanceProductionDto dto) {
        AcceptanceProduction acceptanceProduction = dtoMapper.acceptanceProductionDtoToAcceptanceProduction(dto);
        acceptanceProduction.setProduct(productRepository.getOne(dto.getProductId()));
        return dtoMapper.toAcceptanceProductionDto(acceptanceProductionRepository
                .save(acceptanceProduction));
    }

    @Override
    public AcceptanceProductionDto update(AcceptanceProductionDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        acceptanceProductionRepository.deleteById(id);
    }
}
