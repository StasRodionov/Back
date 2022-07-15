package com.trade_accounting.services.impl.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfProductDto;
import com.trade_accounting.repositories.warehouse.TypeOfProductRepository;
import com.trade_accounting.services.interfaces.warehouse.TypeOfProductService;
import com.trade_accounting.utils.mapper.warehouse.TypeOfProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeOfProductServiceImpl implements TypeOfProductService {

    private final TypeOfProductRepository typeOfProductRepository;

    private final TypeOfProductMapper typeOfProductMapper;

    @Override
    public List<TypeOfProductDto> getAll() {
        return typeOfProductRepository.findAll().stream()
                .map(typeOfProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeOfProductDto getById(Long id) {
        return null;
    }

    @Override
    public TypeOfProductDto create(TypeOfProductDto dto) {
        return null;
    }

    @Override
    public TypeOfProductDto update(TypeOfProductDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
