package com.trade_accounting.services.interfaces.warehouse;


import com.trade_accounting.models.dto.warehouse.KitDto;
import com.trade_accounting.models.entity.warehouse.Kit;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface KitService extends AbstractService<KitDto>, SearchableService<Kit, KitDto> {
}
