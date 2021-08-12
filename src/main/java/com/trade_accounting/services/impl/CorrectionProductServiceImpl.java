package com.trade_accounting.services.impl;

import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.CorrectionProductDto;
import com.trade_accounting.repositories.CorrectionProductRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.CorrectionProductService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CorrectionProductServiceImpl implements CorrectionProductService {

    private final CorrectionProductRepository correctionProductRepository;
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;

    public CorrectionProductServiceImpl(CorrectionProductRepository correctionProductRepository,
                                        ProductRepository productRepository,
                                        DtoMapper dtoMapper) {
        this.correctionProductRepository = correctionProductRepository;
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<CorrectionProductDto> getAll() {
        return correctionProductRepository.findAll().stream()
                .map(dtoMapper::toCorrectionProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public CorrectionProductDto getById(Long id) {
        Optional<CorrectionProduct> correctionProduct = correctionProductRepository.findById(id);
        return dtoMapper.toCorrectionProductDto(correctionProduct.orElse(new CorrectionProduct()));
    }

    @Override
    public CorrectionProductDto create(CorrectionProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public CorrectionProductDto update(CorrectionProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        correctionProductRepository.deleteById(id);
    }

    private CorrectionProductDto saveOrUpdate(CorrectionProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());
        CorrectionProduct correctionProduct = dtoMapper.toCorrectionProduct(dto);
        correctionProduct.setProduct(product.orElse(null));

        return dtoMapper.toCorrectionProductDto(correctionProductRepository.save(correctionProduct));
    }
}
