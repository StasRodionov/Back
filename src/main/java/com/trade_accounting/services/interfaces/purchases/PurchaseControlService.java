package com.trade_accounting.services.interfaces.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.entity.purchases.PurchaseControl;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;
import java.util.Map;

public interface PurchaseControlService extends AbstractService<PurchaseControlDto>, SearchableService<PurchaseControl, PurchaseControlDto> {

    List<PurchaseControlDto> search(String searchTerm);

    List<PurchaseControlDto> getAllForFilter(Map<String, String> map);

}
