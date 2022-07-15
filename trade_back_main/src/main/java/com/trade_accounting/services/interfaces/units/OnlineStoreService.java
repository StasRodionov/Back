package com.trade_accounting.services.interfaces.units;

import com.trade_accounting.models.dto.units.OnlineStoreDto;
import com.trade_accounting.models.entity.units.OnlineStore;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface OnlineStoreService extends AbstractService<OnlineStoreDto>, SearchableService<OnlineStore, OnlineStoreDto> {

    public List<OnlineStoreDto> searchByString(String text);

}
