package com.trade_accounting.utils.mapper.client;

import com.trade_accounting.models.entity.client.Position;
import com.trade_accounting.models.dto.client.PositionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    //Position
    Position toModel(PositionDto position);

    @Mapping(target = "id", source = "position.id")
    PositionDto toDto(Position position);
}
