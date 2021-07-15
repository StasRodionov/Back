package com.trade_accounting.services.impl;

import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.InternalOrderProductsDto;
import com.trade_accounting.repositories.InternalOrderProductRepository;
import com.trade_accounting.repositories.InternalOrderRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.InternalOrderProductService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.mapper.InternalOrderMapper;
import com.trade_accounting.utils.mapper.InternalOrderProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InternalOrderProductServiceImpl implements InternalOrderProductService {
    private final InternalOrderProductRepository internalOrderProductRepository;
    private final ProductRepository productRepository;
    private final InternalOrderProductMapper internalOrderProductMapper;

    @Override
    public List<InternalOrderProductsDto> getAll() {
        return internalOrderProductRepository.findAll().stream()
                .map(internalOrderProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InternalOrderProductsDto getById(Long id) {
        return internalOrderProductMapper.toDto(internalOrderProductRepository.getOne(id));
    }

    @Override
    public InternalOrderProductsDto create(InternalOrderProductsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public InternalOrderProductsDto update(InternalOrderProductsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        internalOrderProductRepository.deleteById(id);
    }

    private InternalOrderProductsDto saveOrUpdate(InternalOrderProductsDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());

        InternalOrderProduct internalOrderProduct = internalOrderProductMapper.toModel(dto);

        internalOrderProduct.setProduct(product.orElse(null));

        return internalOrderProductMapper.toDto(internalOrderProductRepository.save(internalOrderProduct));
    }
}
