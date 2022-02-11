package com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.entity.warehouse.Shipment;
import com.trade_accounting.models.dto.warehouse.ShipmentDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface ShipmentService extends AbstractService<ShipmentDto>, SearchableService<Shipment, ShipmentDto> {

    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);

}
