package com.trade_accounting.services.impl;

import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.repositories.ProductPriceRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.ProductPriceService;
import com.trade_accounting.utils.mapper.ProductPriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductPriceMapper productPriceMapper;
    private final TypeOfPriceRepository typeOfPriceRepository;

    @Override
    public List<ProductPriceDto> getAll() {
        return productPriceRepository.findAll().stream().map(productPriceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductPriceDto getById(Long id) {
        Optional<ProductPrice> productPrice = productPriceRepository.findById(id);
        if (productPrice.isEmpty()) {
           throw  new NullPointerException("productPrice was not found");
        }
        return productPriceMapper.toDto(productPrice.orElse(new ProductPrice()));
    }

    @Override
    public ProductPriceDto create(@NotNull ProductPriceDto dto) {
        ProductPrice productPrice = productPriceMapper.toModel(dto);
        productPrice.setTypeOfPrice(typeOfPriceRepository.getOne(productPrice.getTypeOfPrice().getId()));
        productPriceRepository.save(productPrice);
        return dto;
    }

    @Override
    public ProductPriceDto update(ProductPriceDto dto) {
        ProductPrice productPrice = productPriceMapper.toModel(dto);
        productPrice.setTypeOfPrice(typeOfPriceRepository.getOne(productPrice.getTypeOfPrice().getId()));
        productPriceRepository.save(productPrice);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        productPriceRepository.deleteById(id);
    }
}
