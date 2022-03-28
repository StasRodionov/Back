package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.ShipmentDto;
import com.trade_accounting.models.entity.warehouse.Shipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {
    //Shipment
    Shipment toModel(ShipmentDto emp);

    ShipmentDto toDto(Shipment shipment);
}
