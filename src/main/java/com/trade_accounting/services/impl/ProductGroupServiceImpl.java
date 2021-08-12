package com.trade_accounting.services.impl;

import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.repositories.ProductGroupRepository;
import com.trade_accounting.services.interfaces.ProductGroupService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductGroupServiceImpl implements ProductGroupService {

    private final ProductGroupRepository productGroupRepository;
    private final DtoMapper dtoMapper;

    public ProductGroupServiceImpl(ProductGroupRepository productGroupRepository, DtoMapper dtoMapper) {
        this.productGroupRepository = productGroupRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ProductGroupDto> getAll() {
        return productGroupRepository.findAll().stream()
                .map(dtoMapper::productGroupToProductGroupDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductGroupDto getById(Long id) {
        return dtoMapper.productGroupToProductGroupDto(
                productGroupRepository.findById(id).orElse(new ProductGroup())
        );
    }

    @Override
    public ProductGroupDto create(ProductGroupDto dto) {
        ProductGroup productGroup = dtoMapper.productGroupDtoToProductGroup(dto);

        if (dto.getId() != null) {
            productGroup.setProductGroup(
                    productGroupRepository.findById(dto.getParentId()).orElse(null)
            );
        } else {
            productGroup.setProductGroup(null);
        }
        productGroupRepository.save(productGroup);

        return dto;
    }


    @Override
    public ProductGroupDto update(ProductGroupDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        productGroupRepository.deleteById(id);
    }

}
