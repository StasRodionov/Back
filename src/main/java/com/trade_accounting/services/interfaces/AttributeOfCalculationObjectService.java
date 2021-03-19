package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;

import java.util.List;

public interface AttributeOfCalculationObjectService {

    List<AttributeOfCalculationObjectDto> getAll();

    AttributeOfCalculationObjectDto getById(Long id);

    AttributeOfCalculationObjectDto create(AttributeOfCalculationObjectDto attribute);

    AttributeOfCalculationObjectDto update(AttributeOfCalculationObjectDto attribute);

    void deleteById(Long id);
}
