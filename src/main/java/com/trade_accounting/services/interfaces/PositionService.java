package com.trade_accounting.services.interfaces;


import com.trade_accounting.models.dto.PositionDto;

import java.util.List;

public interface PositionService {
    List<PositionDto> getAll();

    PositionDto getById(Long id);

    PositionDto getByName(String name);

    PositionDto create(PositionDto positionDto);

    PositionDto update(PositionDto positionDto);

    void deleteById(Long id);
}
