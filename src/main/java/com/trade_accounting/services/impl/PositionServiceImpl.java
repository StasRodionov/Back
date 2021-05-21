package com.trade_accounting.services.impl;


import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.Payment;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.services.interfaces.PositionService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    private final DtoMapper dtoMapper;

    public PositionServiceImpl(PositionRepository positionRepository, DtoMapper dtoMapper) {
        this.positionRepository = positionRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<PositionDto> getAll() {
        return positionRepository.findAll().stream()
                .map(dtoMapper::positionToPositionDto)
                .collect(Collectors.toList());
    }

    @Override
    public PositionDto getById(Long id) {
        Optional<Position> position = positionRepository.findById(id);
        if(position.isEmpty()){
            throw new NotFoundEntityException("There is not position with “id”");
        }
        return dtoMapper.positionToPositionDto(position.get());
    }

    @Override
    public PositionDto getByName(String name) {
        return dtoMapper.positionToPositionDto(
                positionRepository.findByName(name).orElse(new Position())
        );
    }

    @Override
    public PositionDto create(PositionDto positionDto) {
        Position position = dtoMapper.positionDtoToPosition(positionDto);
        return dtoMapper.positionToPositionDto(
                positionRepository.save(position)
        );
    }

    @Override
    public PositionDto update(PositionDto positionDto) {
        return create(positionDto);
    }

    @Override
    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }
}
