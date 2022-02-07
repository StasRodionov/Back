package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ShipmentDto;
import com.trade_accounting.services.impl.Stubs.model.ShipmentModelStubs;
import com.trade_accounting.utils.mapper.ShipmentMapper;
import org.mapstruct.factory.Mappers;

public class ShipmentDtoStubs {
    private static final ShipmentMapper mapper = Mappers.getMapper(ShipmentMapper.class);

    public static ShipmentDto getShipmentDro(Long id){
        return mapper.toDto(ShipmentModelStubs.getShipment(id));
    }
}
