package com.trade_accounting.services.impl;

import com.trade_accounting.models.Correction;
import com.trade_accounting.models.dto.CorrectionDto;
import com.trade_accounting.repositories.CorrectionRepository;
import com.trade_accounting.services.interfaces.CorrectionService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorrectionServiceImpl implements CorrectionService {

    private final CorrectionRepository correctionRepository;
    private final DtoMapper dtoMapper;

    public CorrectionServiceImpl(CorrectionRepository correctionRepository, DtoMapper dtoMapper) {
        this.correctionRepository = correctionRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CorrectionDto> getAll() {
        return correctionRepository.getAll().stream()
                .map(dtoMapper::toCorrectionDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CorrectionDto getById(Long id) {
        Correction correction = correctionRepository.getCorrectionById(id);
        return dtoMapper.toCorrectionDto(correction);
    }

    @Transactional
    @Override
    public CorrectionDto create(CorrectionDto dto) {
        Correction correctionSave = correctionRepository.save(dtoMapper.toCorrection(dto));
        return dtoMapper.toCorrectionDto(correctionSave);
    }

    @Transactional
    @Override
    public CorrectionDto update(CorrectionDto dto) {
        Correction correctionUpdate = correctionRepository.save(dtoMapper.toCorrection(dto));
        return dtoMapper.toCorrectionDto(correctionUpdate);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        correctionRepository.deleteById(id);
    }
}

