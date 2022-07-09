package com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface PriceListService extends AbstractService<PriceListDto>, SearchableService<PriceList, PriceListDto> {

    List<PriceListDto> getAllForFilter(String string);

    void moveToRecyclebin(long id);

    void restoreFromRecyclebin(long id);

    List<PriceListDto> quickSearch(String text);
}
