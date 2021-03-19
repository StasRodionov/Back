package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final DtoMapper dtoMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              DtoMapper dtoMapper) {
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.getAll();
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.getOne(id);

        ProductDto productDto = dtoMapper.productToProductDto(productRepository.getOne(id));
        productDto.setImageDtos(dtoMapper.toImageDto(product.getImages()));

        return productDto;
    }

    @Override
    public List<ProductDto> getAllByProductGroupId(Long id) {
        return productRepository.getAllByProductGroupId(id);
    }

    @Override
    public void save(ProductDto productDto) {
        List<Image> images = dtoMapper.toImage(productDto.getImageDtos(), "product");

        Product product = dtoMapper.productDtoToProduct(productDto);
        product.setImages(images);

        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllByContractorId(Long id) {
        return productRepository.getAllByContractorId(id);
    }
}
