package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.BuyersReturn;
import com.trade_accounting.models.dto.BuyersReturnDto;

import java.util.List;

public interface BuyersReturnService extends AbstractService<BuyersReturnDto>, SearchableService<BuyersReturn, BuyersReturnDto> {
    List<BuyersReturnDto> findBySearch(String text);
}
