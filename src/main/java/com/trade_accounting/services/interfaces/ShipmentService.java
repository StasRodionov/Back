package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Shipment;
import com.trade_accounting.models.dto.ShipmentDto;

public interface ShipmentService extends AbstractService<ShipmentDto>, SearchableService<Shipment, ShipmentDto> {

}
