package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.models.dto.ShipmentProductDto;
import com.trade_accounting.services.impl.Stubs.model.RetailSalesModelStubs;
import com.trade_accounting.services.impl.Stubs.model.ShipmentProductModelStubs;
import com.trade_accounting.utils.mapper.RetailSalesMapper;
import com.trade_accounting.utils.mapper.ShipmentProductMapper;
import org.mapstruct.factory.Mappers;

public class ShipmentProductDtoStubs {
    private static final ShipmentProductMapper mapper = Mappers.getMapper(ShipmentProductMapper.class);

    public static ShipmentProductDto getDto(Long id) {
        return mapper.toDto(ShipmentProductModelStubs.getShipmentProduct(id));
    }
}
