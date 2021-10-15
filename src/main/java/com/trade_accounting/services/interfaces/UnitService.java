package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Unit;
import com.trade_accounting.models.dto.UnitDto;

import java.util.List;

public interface UnitService extends AbstractService<UnitDto>, SearchableService<Unit, UnitDto> {

    List<UnitDto> searchByString(String text);

}
