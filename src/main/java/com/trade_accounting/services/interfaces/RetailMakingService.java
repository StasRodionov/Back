package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.RetailMaking;
import com.trade_accounting.models.dto.RetailMakingDto;

import java.util.List;

public interface RetailMakingService extends AbstractService<RetailMakingDto>, SearchableService<RetailMaking, RetailMakingDto> {

    List<RetailMakingDto> search(String search);

}
