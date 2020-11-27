package com.trade_accounting.services.impl;

import com.trade_accounting.models.TaxSystem;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.repositories.TaxSystemRepository;
import com.trade_accounting.services.interfaces.TaxSystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class TaxSystemServiceImpl implements TaxSystemService {

    private final TaxSystemRepository taxSystemRepository;

    public TaxSystemServiceImpl(TaxSystemRepository taxSystemRepository) {
        this.taxSystemRepository = taxSystemRepository;
    }

    @Override
    public List<TaxSystemDto> getAll() {
        return taxSystemRepository.getAll();
    }

    @Override
    public TaxSystemDto getById(Long id) {
        return taxSystemRepository.getById(id);
    }

    @Override
    public void create(TaxSystemDto dto) {
        TaxSystem taxSystem = new TaxSystem(
                null,
                dto.getName(),
                dto.getSortNumber()
        );
        taxSystemRepository.save(taxSystem);
    }

    @Override
    public void update(TaxSystemDto dto) {
        TaxSystem taxSystem = new TaxSystem(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber()
        );
        taxSystemRepository.save(taxSystem);
    }

    @Override
    public void deleteById(Long id) {
        taxSystemRepository.deleteById(id);
    }
}
