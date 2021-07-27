package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalCardGroup;
import com.trade_accounting.models.dto.TechnicalCardGroupDto;
import com.trade_accounting.repositories.TechnicalCardGroupRepository;
import com.trade_accounting.services.interfaces.TechnicalCardGroupService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TechnicalCardGroupServiceImpl implements TechnicalCardGroupService {

    private final TechnicalCardGroupRepository technicalCardGroupRepository;

    private final DtoMapper dtoMapper;

    public TechnicalCardGroupServiceImpl(TechnicalCardGroupRepository technicalCardGroupRepository, DtoMapper dtoMapper) {
        this.technicalCardGroupRepository = technicalCardGroupRepository;
        this.dtoMapper = dtoMapper;
    }


    @Override
    public List<TechnicalCardGroupDto> getAll() {
        return technicalCardGroupRepository.findAll().stream()
                .map(dtoMapper::technicalCardGroupToTechnicalCardGroupDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalCardGroupDto getById(Long id) {
        return dtoMapper.technicalCardGroupToTechnicalCardGroupDto(
                technicalCardGroupRepository.getOne(id));
    }

    @Override
    public TechnicalCardGroupDto create(TechnicalCardGroupDto dto) {
        return dtoMapper.technicalCardGroupToTechnicalCardGroupDto(technicalCardGroupRepository
                .save(dtoMapper.technicalCardGroupDtoToTechnicalCardGroup(dto)));
    }

    @Override
    public TechnicalCardGroupDto update(TechnicalCardGroupDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        technicalCardGroupRepository.deleteById(id);
    }
}
