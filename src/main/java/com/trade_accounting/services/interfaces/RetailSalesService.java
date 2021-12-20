package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.RetailSales;
import com.trade_accounting.models.TechnicalOperations;
import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.models.dto.TechnicalOperationsDto;

import java.util.List;

public interface RetailSalesService extends  AbstractService<RetailSalesDto>, SearchableService<RetailSales, RetailSalesDto>{

    List<RetailSalesDto> search(String searchTerm);

}
