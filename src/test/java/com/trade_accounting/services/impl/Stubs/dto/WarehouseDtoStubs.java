package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.services.impl.Stubs.model.WarehouseModelStubs;
import com.trade_accounting.utils.mapper.WarehouseMapper;
import org.mapstruct.factory.Mappers;

public class WarehouseDtoStubs {

    private static final WarehouseMapper mapper = Mappers.getMapper(WarehouseMapper.class);

    public static WarehouseDto getDto(Long id) {
        return mapper.toDto(WarehouseModelStubs.getWarehouse(id));
    }
}
