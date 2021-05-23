package com.trade_accounting.services.impl;

import com.trade_accounting.models.Status;
import com.trade_accounting.models.dto.StatusDto;
import com.trade_accounting.repositories.StatusRepository;
import com.trade_accounting.services.interfaces.StatusService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    private final DtoMapper dtoMapper;

    public StatusServiceImpl(StatusRepository statusRepository, DtoMapper dtoMapper) {
        this.statusRepository = statusRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public StatusDto getById(Long id) {
        return dtoMapper.statusToStatusDto(statusRepository.findById(id).orElse(new Status()));
    }

    @Override
    public StatusDto create(StatusDto dto) {
        return dtoMapper.statusToStatusDto(statusRepository.save(dtoMapper.statusDtoToStatus(dto)));
    }

    @Override
    public StatusDto update(StatusDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public List<StatusDto> getAll() {
        return statusRepository.findAll().stream()
                .map(dtoMapper::statusToStatusDto)
                .collect(Collectors.toList());
    }
}
