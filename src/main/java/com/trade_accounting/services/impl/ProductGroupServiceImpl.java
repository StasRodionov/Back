package com.trade_accounting.services.impl;

import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.repositories.ProductGroupRepository;
import com.trade_accounting.services.interfaces.ProductGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductGroupServiceImpl implements ProductGroupService {

    private final ProductGroupRepository productGroupRepository;

    public ProductGroupServiceImpl(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    @Override
    public List<ProductGroupDto> getAll() {
        return productGroupRepository.getAll();
    }

    @Override
    public ProductGroupDto getById(Long id) {
        return productGroupRepository.getById(id);
    }

    @Override
    public void create(ProductGroupDto dto) {
        productGroupRepository.save(new ProductGroup(
                dto.getName(),
                dto.getSortNumber(),
                productGroupRepository.getOne(dto.getParentId()))
        );
    }

    @Override
    public void update(ProductGroupDto dto) {
        productGroupRepository.save(new ProductGroup(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber(),
                productGroupRepository.getOne(dto.getParentId()))
        );
    }

    @Override
    public void deleteById(Long id) {
        productGroupRepository.deleteById(id);
    }
}
