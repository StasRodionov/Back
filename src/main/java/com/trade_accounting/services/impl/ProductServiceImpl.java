package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.PageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    private final DtoMapper dtoMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ImageRepository imageRepository, DtoMapper dtoMapper) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(dtoMapper::productToProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.getOne(id);

        ProductDto productDto = dtoMapper.productToProductDto(product);
        productDto.setImageDtos(dtoMapper.toImageDto(product.getImages()));

        return productDto;
    }

    @Override
    public ProductDto create(ProductDto dto) {
        List<Image> preparedImages = dtoMapper.toImage(dto.getImageDtos(), "product");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        Product product = dtoMapper.productDtoToProduct(dto);
        product.setImages(savedImages);
        productRepository.saveAndFlush(product);
        return dto;
    }

    @Override
    public ProductDto update(ProductDto dto) {
        List<Image> preparedImages = dtoMapper.toImage(dto.getImageDtos(), "product");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        Product product = dtoMapper.productDtoToProduct(dto);
        product.setImages(savedImages);
        productRepository.saveAndFlush(product);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public List<ProductDto> search(Specification<Product> spec) {
        List<Product> productList = productRepository.findAll(spec);
        return dtoMapper.toProductDto(productList);
    }

    @Override
    public PageDto<ProductDto> search(Specification<Product> specification, Pageable pageParam) {
        Page<Product> page = productRepository.findAll(specification, pageParam);
        return new PageDto<>(
                page.getContent().stream().map(dtoMapper::productToProductDto).collect(Collectors.toList()),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumberOfElements()
        );
    }

    @Override
    public List<ProductDto> search(String value) {
        return dtoMapper.toProductDto(productRepository.search(value));
    }
}
