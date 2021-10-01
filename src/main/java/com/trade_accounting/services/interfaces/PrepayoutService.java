package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Prepayout;
import com.trade_accounting.models.dto.PrepayoutDto;


public interface PrepayoutService extends AbstractService<PrepayoutDto>, SearchableService<Prepayout, PrepayoutDto> {
}
