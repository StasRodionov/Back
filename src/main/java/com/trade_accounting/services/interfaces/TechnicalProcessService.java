package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.TechnicalProcess;
import com.trade_accounting.models.dto.TechnicalProcessDto;

import java.util.List;

public interface TechnicalProcessService extends AbstractService<TechnicalProcessDto>, SearchableService<TechnicalProcess, TechnicalProcessDto> {
    List<TechnicalProcessDto> search(String request);
    TechnicalProcessDto getByName(String name);
}
