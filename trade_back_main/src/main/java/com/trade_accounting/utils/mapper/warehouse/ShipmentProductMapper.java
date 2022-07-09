package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.ShipmentProduct;
import com.trade_accounting.models.dto.warehouse.ShipmentProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentProductMapper {
    //ShipmentProduct
    ShipmentProduct toModel(ShipmentProductDto shipmentProductDto);

    @Mapping(target = "productId", source = "product.id")
    ShipmentProductDto toDto(ShipmentProduct shipmentProduct);
}
