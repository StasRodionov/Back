package com.trade_accounting.services.impl;

import com.trade_accounting.models.InventarizationProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.InventarizationProductDto;
import com.trade_accounting.repositories.InventarizationProductRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.InventarizationProductService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventarizationProductServiceImpl implements InventarizationProductService {

    private final InventarizationProductRepository inventarizationProductRepository;
    private final DtoMapper dtoMapper;
    private final ProductRepository productRepository;

    public InventarizationProductServiceImpl(InventarizationProductRepository inventarizationProductRepository,
                                             DtoMapper dtoMapper,
                                             ProductRepository productRepository) {
        this.inventarizationProductRepository = inventarizationProductRepository;
        this.dtoMapper = dtoMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<InventarizationProductDto> getAll() {

        List<InventarizationProductDto> inventarizationProductDtos = inventarizationProductRepository.findAll()
                .stream()
                .map(dtoMapper::toInventarizationProductDto)
                .collect(Collectors.toList());

        return inventarizationProductDtos;
    }

    @Override
    public InventarizationProductDto getById(Long id) {
        Optional<InventarizationProduct> inventarizationProduct = inventarizationProductRepository.findById(id);
        return dtoMapper.toInventarizationProductDto(inventarizationProduct.orElse(new InventarizationProduct()));
    }

    @Override
    public InventarizationProductDto create(InventarizationProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public InventarizationProductDto update(InventarizationProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        inventarizationProductRepository.deleteById(id);
    }

    public InventarizationProductDto saveOrUpdate(InventarizationProductDto dto) {
        InventarizationProduct inventarizationProduct = dtoMapper.toInventarizationProduct(dto);
        Optional<Product> product = productRepository.findById(dto.getProductId());
        inventarizationProduct.setProduct(product.orElse(null));

        return dtoMapper.toInventarizationProductDto(inventarizationProductRepository.save(inventarizationProduct));
    }
}
