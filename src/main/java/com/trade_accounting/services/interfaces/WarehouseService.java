package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.WarehouseDto;

import java.util.List;

public interface WarehouseService extends AbstractService<WarehouseDto>, SearchableService<Warehouse, WarehouseDto> {

    List<WarehouseDto> searchByString(String text);

}
