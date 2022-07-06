package com.trade_accounting.services.impl.company;


import com.trade_accounting.models.dto.company.SaleTaxDto;
import com.trade_accounting.repositories.company.SaleTaxRepository;
import com.trade_accounting.services.interfaces.company.SaleTaxService;
import com.trade_accounting.utils.mapper.company.SaleTaxMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleTaxServiceImpl implements SaleTaxService {

    private final SaleTaxRepository saleTaxRepository;

    private final SaleTaxMapper saleTaxMapper;


    @Override
    public List<SaleTaxDto> getAll() {
        return saleTaxRepository.findAll().stream()
                .map(saleTaxMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SaleTaxDto getById(Long id) {
        return null;
    }

    @Override
    public SaleTaxDto create(SaleTaxDto dto) {
        return null;
    }

    @Override
    public SaleTaxDto update(SaleTaxDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
