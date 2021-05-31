package com.trade_accounting.services.impl;

import com.trade_accounting.models.Product;
import com.trade_accounting.models.Production;
import com.trade_accounting.models.dto.PageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.ProductionDto;
import com.trade_accounting.repositories.ProductionRepository;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.services.interfaces.ProductionService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;

    private final DtoMapper dtoMapper;

    public ProductionServiceImpl(ProductionRepository productionRepository, DtoMapper dtoMapper){
        this.productionRepository = productionRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ProductionDto> getAll() {
        /*
        final List<ProductionDto> collect = productionRepository.findAll().stream()
                .map(dtoMapper::productionToProductionDto)
                .collect(Collectors.toList());
        return collect;

         */
        return null;
    }


    @Override
    public ProductionDto getById(Long id) {
        /*
                return dtoMapper.projectToProjectDto(
                projectRepository.findById(id).orElse(new Project())
        );
         */
       return null;
    }

    @Override
    public ProductionDto create(ProductionDto dto) {
        /*

        Production production = productionRepository.save(
                dtoMapper.productionDtoToProduction(dto)
        );
        dto.setId(production.getId());
        return dtoMapper.productionToProductionDto(production);
         */
        return null;
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
