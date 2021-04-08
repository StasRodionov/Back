package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAll();

    ProductDto getById(Long id);

    void save(ProductDto productDto);

    void deleteById(Long id);

    List<ProductDto> getAllByProductGroupId(Long id);

    List<ProductDto> getAllByContractorId(Long id);

    List<ProductDto> search(String value);
}