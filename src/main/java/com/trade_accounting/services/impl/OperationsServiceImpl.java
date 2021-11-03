package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.OperationsDto;
import com.trade_accounting.repositories.OperationsRepository;
import com.trade_accounting.services.interfaces.OperationsService;
import com.trade_accounting.utils.mapper.OperationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationsServiceImpl implements OperationsService {

    private final OperationsRepository operationAbstractRepository;
    private final OperationsMapper operationsMapper;

    public List<OperationsDto> getAll() {
        return operationAbstractRepository.findAll().stream()
                .map(operationsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OperationsDto getById(Long id) {
        return null;
    }

    @Override
    public OperationsDto create(OperationsDto dto) {
        return null;
    }

    @Override
    public OperationsDto update(OperationsDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
