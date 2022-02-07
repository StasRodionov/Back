package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Shipment;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getContractor;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getWarehouse;

public class ShipmentModelStubs {
    public static Shipment getShipment(Long id){
        return Shipment.builder()
                .id(id)
                .contractor(getContractor(id))
                .warehouse(getWarehouse(id))
                .paid(new BigDecimal(198))
                .shipmentProducts(new ArrayList<>())
                .build();
    }
}
