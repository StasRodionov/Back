package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.MovementProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.warehouse.MovementProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementProductMapper {
    //MovementProduct
    @Mapping(source = "productId", target = "product.id")
    MovementProduct toModel(MovementProductDto movementProductDto);

    @Mapping(source = "product.id", target = "productId")
    MovementProductDto toDto(MovementProduct movementProduct);
}
