package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.RetailShift;
import com.trade_accounting.models.TechnicalOperations;
import com.trade_accounting.models.dto.RetailShiftDto;
import com.trade_accounting.models.dto.TechnicalOperationsDto;

import java.util.List;

public interface RetailShiftService extends AbstractService<RetailShiftDto>{

    List<RetailShiftDto> search(String searchTerm);
}
