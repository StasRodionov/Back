package com.trade_accounting.services.impl;

import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.PageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.repositories.ProductionRepository;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductionServiceImpl implements ProductService {

    private final ProductionRepository productionRepository;

    private final DtoMapper dtoMapper;

    public ProductionServiceImpl(ProductionRepository productionRepository, DtoMapper dtoMapper){
        this.productionRepository = productionRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ProductDto> getAll() {
        return null;
    }

    @Override
    public ProductDto getById(Long id) {
        return null;
    }

    @Override
    public ProductDto create(ProductDto dto) {
        return null;
    }

    @Override
    public ProductDto update(ProductDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public PageDto<ProductDto> search(Specification<Product> specification, Pageable page) {
        return null;
    }

    @Override
    public List<ProductDto> search(String value) {
        return null;
    }

    @Override
    public List<ProductDto> search(Specification<Product> spec) {
        return null;
    }
}
