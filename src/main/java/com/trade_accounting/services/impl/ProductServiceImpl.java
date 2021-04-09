package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.utils.DtoMapper;
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
        List<Image> preparedImages = dtoMapper.toImage(productDto.getImageDtos(), "product");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        Product product = dtoMapper.productDtoToProduct(productDto);
        product.setImages(savedImages);
        productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllByContractorId(Long id) {
        return productRepository.getAllByContractorId(id);
    }

    @Override
    public List<ProductDto> search(String value) {
        return productRepository.search(value);
    }

    @Override
    public List<ProductDto> searchByFilter(Specification<Product> spec) {
        return productRepository.findAll(spec)
                .stream()
                .map(dtoMapper::productToProductDto)
                .collect(Collectors.toList());
    }
}
