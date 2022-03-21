package com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import java.util.List;

public interface PriceListService extends AbstractService<PriceListDto> {
    List<PriceListDto> getAllForFilter(String string);
}
