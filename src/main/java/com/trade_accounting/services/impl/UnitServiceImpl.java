package com.trade_accounting.services.impl;

import com.trade_accounting.models.Unit;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.services.interfaces.UnitService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.SortNumberConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    private final DtoMapper dtoMapper;

    public UnitServiceImpl(UnitRepository unitRepository, DtoMapper dtoMapper) {
        this.unitRepository = unitRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<UnitDto> getAll() {
        return unitRepository.findAll().stream()
                .map(dtoMapper::unitToUnitDto)
                .collect(Collectors.toList());
    }

    @Override
    public UnitDto getById(Long id) {
        return dtoMapper.unitToUnitDto(
                unitRepository.findById(id).orElse(new Unit())
        );
    }

    @Override
    public UnitDto create(UnitDto unitDto) {
        Unit unit = dtoMapper.unitDtoToUnit(unitDto);

        unit.setSortNumber(
                SortNumberConverter.convert(unitDto.getSortNumber())
        );

        unitRepository.save(unit);
        return unitDto;
    }

    @Override
    public UnitDto update(UnitDto unitDto) {
        create(unitDto);
        return unitDto;
    }

    @Override
    public void deleteById(Long id) {
        unitRepository.deleteById(id);
    }
}
