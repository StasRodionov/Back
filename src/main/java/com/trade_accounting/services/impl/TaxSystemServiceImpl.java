package com.trade_accounting.services.impl;

import com.trade_accounting.models.TaxSystem;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.repositories.TaxSystemRepository;
import com.trade_accounting.services.interfaces.TaxSystemService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class TaxSystemServiceImpl implements TaxSystemService {

    private final TaxSystemRepository taxSystemRepository;
    private final DtoMapper dtoMapper;

    public TaxSystemServiceImpl(TaxSystemRepository taxSystemRepository, DtoMapper dtoMapper) {
        this.taxSystemRepository = taxSystemRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<TaxSystemDto> getAll() {
        return taxSystemRepository.findAll().stream()
                .map(dtoMapper::taxSystemToTaxSystemDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaxSystemDto getById(Long id) {
        return dtoMapper.taxSystemToTaxSystemDto(
                taxSystemRepository.findById(id).orElse(new TaxSystem()));
    }

    @Override
    public TaxSystemDto create(TaxSystemDto taxSystemDto) {
        TaxSystem taxSystem = dtoMapper.taxSystemDtoToTaxSystem(taxSystemDto);
        taxSystemDto.setId(taxSystem.getId());
        return dtoMapper.taxSystemToTaxSystemDto(
                taxSystemRepository.save(taxSystem));
    }


    @Override
    public TaxSystemDto update(TaxSystemDto taxSystemDto) {
        return create(taxSystemDto);

    }

    @Override
    public void deleteById(Long id) {
        taxSystemRepository.deleteById(id);
    }
}
