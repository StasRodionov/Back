package com.trade_accounting.services.impl.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfAccountingDto;
import com.trade_accounting.repositories.warehouse.TypeOfAccountingRepository;
import com.trade_accounting.services.interfaces.warehouse.TypeOfAccountingService;
import com.trade_accounting.utils.mapper.warehouse.TypeOfAccountingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeOfAccountingServiceImpl implements TypeOfAccountingService {

    private final TypeOfAccountingRepository typeOfAccountingRepository;

    private final TypeOfAccountingMapper typeOfAccountingMapper;

    @Override
    public List<TypeOfAccountingDto> getAll() {
        return typeOfAccountingRepository.findAll().stream()
                .map(typeOfAccountingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeOfAccountingDto getById(Long id) {
        return null;
    }

    @Override
    public TypeOfAccountingDto create(TypeOfAccountingDto dto) {
        return null;
    }

    @Override
    public TypeOfAccountingDto update(TypeOfAccountingDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
