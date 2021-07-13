package com.trade_accounting.services.impl;

import com.trade_accounting.models.MovementProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.MovementProductDto;
import com.trade_accounting.repositories.MovementProductRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.MovementProductService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovementProductServiceImpl implements MovementProductService {

    private final MovementProductRepository movementProductRepository;
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;

    public MovementProductServiceImpl(MovementProductRepository movementProductRepository,
                                      ProductRepository productRepository,
                                      DtoMapper dtoMapper) {
        this.movementProductRepository = movementProductRepository;
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<MovementProductDto> getAll() {
        return movementProductRepository.findAll().stream()
                .map(dtoMapper::toMovementProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovementProductDto getById(Long id) {
        Optional<MovementProduct> movementProduct = movementProductRepository.findById(id);
        return dtoMapper.toMovementProductDto(movementProduct.orElse(new MovementProduct()));
    }

    @Override
    public MovementProductDto create(MovementProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public MovementProductDto update(MovementProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        movementProductRepository.deleteById(id);
    }

    private MovementProductDto saveOrUpdate(MovementProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());
        MovementProduct movementProduct = dtoMapper.toMovementProduct(dto);
        movementProduct.setProduct(product.orElse(null));

        return dtoMapper.toMovementProductDto(movementProductRepository.save(movementProduct));
    }
}
