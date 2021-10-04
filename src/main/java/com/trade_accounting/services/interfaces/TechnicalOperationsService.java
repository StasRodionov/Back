package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.TechnicalOperations;
import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.models.dto.TechnicalOperationsDto;

import java.util.List;


public interface TechnicalOperationsService extends AbstractService<TechnicalOperationsDto>, SearchableService<TechnicalOperations, TechnicalOperationsDto>{

     List<TechnicalOperationsDto> search(String searchTerm);

//     List<TechnicalOperationsDto> searchByString(String nameFilter);
}
