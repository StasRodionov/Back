package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Movement;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.models.entity.warehouse.MovementProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    //Movement
    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "warehouseToId", target = "warehouseTo.id")
    @Mapping(source = "projectId", target = "project.id")
    Movement toModel(MovementDto movementDto);

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "warehouseToId", source = "warehouseTo.id")
    @Mapping(target = "employeeChangedId", source = "employeeChanged.id")
    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "movementProductsIds", source = "movementProducts")
    MovementDto toDto(Movement movement);

    default Long movementProductToLong(MovementProduct movementProduct) {
        return movementProduct.getId();
    }
}
