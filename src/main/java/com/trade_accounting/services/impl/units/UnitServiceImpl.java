package com.trade_accounting.services.impl.units;

import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import com.trade_accounting.repositories.units.UnitRepository;
import com.trade_accounting.services.interfaces.units.UnitService;
import com.trade_accounting.utils.SortNumberConverter;
import com.trade_accounting.utils.mapper.units.UnitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    private final UnitMapper unitMapper;

    @Override
    public List<UnitDto> getAll() {
        return unitRepository.findAll().stream()
                .map(unitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UnitDto getById(Long id) {
//        Optional<Unit> unit = unitRepository.findById(id);
//        return unitMapper.toDto(
//                unit.orElse(new Unit())
//        );
        return unitMapper.toDto(unitRepository.findById(id).orElse(new Unit()));
    }

    @Override
    public UnitDto create(UnitDto unitDto) {
        Unit unit = unitMapper.toModel(unitDto);

        unit.setSortNumber(
                SortNumberConverter.convert(unitDto.getSortNumber())
        );
        unitDto.setId(unit.getId());
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

    @Override
    public List<UnitDto> search(Specification<Unit> spec) {
        return executeSearch(unitRepository, unitMapper::toDto, spec);
    }

    @Override
    public List<UnitDto> searchByString(String text) {
        return unitRepository.getBySearch(text).stream()
                .map(unitMapper::toDto)
                .collect(Collectors.toList());

    }
}
