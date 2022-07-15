package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    //Warehouse
    Warehouse toModel(WarehouseDto warehouseDto);

    WarehouseDto toDto(Warehouse warehouse);
}
