package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.RetailReturn;
import com.trade_accounting.models.dto.RetailReturnDto;
import com.trade_accounting.models.dto.RetailSalesDto;

import java.util.List;

public interface RetailReturnService extends AbstractService<RetailReturnDto>, SearchableService<RetailReturn, RetailReturnDto> {

    List<RetailReturnDto> search(String searchTerm);

}
