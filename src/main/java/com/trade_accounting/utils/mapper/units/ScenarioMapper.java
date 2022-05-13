package com.trade_accounting.utils.mapper.units;


import com.trade_accounting.models.dto.units.ScenarioDto;

import com.trade_accounting.models.entity.units.Scenario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScenarioMapper {
    //Scenario
    Scenario toModel(ScenarioDto scenarioDto);

    ScenarioDto toDto(Scenario scenario);
}
