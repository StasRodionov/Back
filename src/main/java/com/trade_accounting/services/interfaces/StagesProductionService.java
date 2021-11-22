package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.StagesProduction;
import com.trade_accounting.models.dto.StagesProductionDto;

import java.util.List;


public interface StagesProductionService extends AbstractService <StagesProductionDto>, SearchableService<StagesProduction, StagesProductionDto>{

    List<StagesProductionDto> search(String searchTerm);


}
