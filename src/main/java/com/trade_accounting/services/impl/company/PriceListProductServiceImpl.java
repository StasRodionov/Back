package com.trade_accounting.services.impl.company;

import com.trade_accounting.models.dto.company.PriceListProductDto;
import com.trade_accounting.models.entity.company.PriceListProduct;
import com.trade_accounting.repositories.company.PriceListProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.services.interfaces.company.PriceListProductService;
import com.trade_accounting.utils.mapper.company.PriceListProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceListProductServiceImpl implements PriceListProductService {

    private final PriceListProductRepository priceListProductRepository;
    private final PriceListProductMapper priceListProductMapper;
    private final ProductRepository productRepository;


    @Override
    public List<PriceListProductDto> getAll() {
        return priceListProductRepository.findAll().stream()
                .map(priceListProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PriceListProductDto getById(Long id) {
        return priceListProductMapper.toDto(priceListProductRepository
                .findById(id).orElse(new PriceListProduct()));
    }

    @Override
    public PriceListProductDto create(@NotNull PriceListProductDto dto) {
        PriceListProduct priceListProduct = priceListProductMapper.toModel(dto);
        priceListProduct.setProducts(productRepository.findById(dto.getProductId()).orElse(null));
        return priceListProductMapper.toDto(priceListProductRepository.save(priceListProduct));
    }

    @Override
    public PriceListProductDto update(@NotNull PriceListProductDto dto) {
        PriceListProduct priceListProduct = priceListProductMapper.toModel(dto);
        priceListProduct.setProducts(productRepository.findById(dto.getProductId()).orElse(null));
        return priceListProductMapper.toDto(priceListProductRepository.save(priceListProduct));
    }

    @Override
    public void deleteById(Long id) {
        priceListProductRepository.deleteById(id);
    }

    @Override
    public List<PriceListProductDto> search(Specification<PriceListProduct> spec) {
        return priceListProductRepository.findAll(spec).stream()
                .map(priceListProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PriceListProductDto> quickSearch(String text) {
        return priceListProductRepository.getBySearch(text).stream()
                .map(priceListProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceListProductDto> getAllByProductId(Long id) {
        return priceListProductRepository.findAllByProductsId(id).stream().map(priceListProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createAll(List<PriceListProductDto> priceListProductDtos) {
        priceListProductRepository.saveAll(
                priceListProductDtos.stream()
                        .map(priceListProductMapper::toModel)
                        .collect(Collectors.toList())
        );
    }
}
