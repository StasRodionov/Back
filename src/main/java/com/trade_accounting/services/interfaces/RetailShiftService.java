package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.RetailShift;
import com.trade_accounting.models.dto.RetailShiftDto;

import java.util.List;

public interface RetailShiftService extends AbstractService<RetailShiftDto>, SearchableService<RetailShift, RetailShiftDto> {

    List<RetailShiftDto> search(String searchTerm);
}
