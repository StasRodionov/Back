package com.trade_accounting.services.interfaces.units;

import com.trade_accounting.models.dto.units.ScenarioDto;
import com.trade_accounting.models.entity.units.Scenario;

import java.util.List;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface ScenarioService extends AbstractService<ScenarioDto>, SearchableService<Scenario, ScenarioDto>{

    List<ScenarioDto> searchByString(String text);
}
