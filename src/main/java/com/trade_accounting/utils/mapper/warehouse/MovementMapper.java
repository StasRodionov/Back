package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Movement;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    //Movement
    @Mapping(target = "date", ignore = true)
    Movement toModel(MovementDto movementDto);

    MovementDto toDto(Movement movement);
}
