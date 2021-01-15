package com.trade_accounting.services.impl;


import com.trade_accounting.models.Position;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.services.interfaces.PositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public List<PositionDto> getAll() {
        return positionRepository.getAll();
    }

    @Override
    public PositionDto getById(Long id) {
        return positionRepository.getById(id);
    }

    @Override
    public PositionDto getByName(String name) {
        return positionRepository.getByName(name);
    }

    @Override
    public void create(PositionDto positionDto) {
        positionRepository.save(
                new Position(
                        positionDto.getName(),
                        positionDto.getSortNumber()
                )
        );
    }

    @Override
    public void update(PositionDto positionDto) {
        positionRepository.save(
                new Position(
                        positionDto.getId(),
                        positionDto.getName(),
                        positionDto.getSortNumber()
                )

        );

    }

    @Override
    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }
}
