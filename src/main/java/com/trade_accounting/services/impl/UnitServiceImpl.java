package com.trade_accounting.services.impl;

import com.trade_accounting.models.Unit;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.services.interfaces.UnitService;
import com.trade_accounting.utils.SortNumberConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public List<UnitDto> getAll() {
        return unitRepository.getAll();
    }

    @Override
    public UnitDto getById(Long id) {
        return unitRepository.getById(id);
    }

    @Override
    public void create(UnitDto unitDto) {
        unitRepository.save(
                new Unit(
                        unitDto.getShortName(),
                        unitDto.getFullName(),
                        SortNumberConverter.convert(unitDto.getSortNumber())
                )
        );
    }

    @Override
    public void update(UnitDto unitDto) {
        unitRepository.save(
                new Unit(
                        unitDto.getId(),
                        unitDto.getShortName(),
                        unitDto.getFullName(),
                        SortNumberConverter.convert(unitDto.getSortNumber())
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        unitRepository.deleteById(id);
    }
}
