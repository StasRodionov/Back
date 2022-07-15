package com.trade_accounting.services.impl.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfPackingDto;
import com.trade_accounting.repositories.warehouse.TypeOfPackingRepository;
import com.trade_accounting.services.interfaces.warehouse.TypeOfPackingService;
import com.trade_accounting.utils.mapper.warehouse.TypeOfPackingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeOfPackingServiceImpl implements TypeOfPackingService {

    private final TypeOfPackingRepository typeOfPackingRepository;

    private final TypeOfPackingMapper typeOfPackingMapper;


    @Override
    public List<TypeOfPackingDto> getAll() {
        return typeOfPackingRepository.findAll().stream()
                .map(typeOfPackingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeOfPackingDto getById(Long id) {
        return null;
    }

    @Override
    public TypeOfPackingDto create(TypeOfPackingDto dto) {
        return null;
    }

    @Override
    public TypeOfPackingDto update(TypeOfPackingDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
