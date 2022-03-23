package com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    //Unit
    Unit toModel(UnitDto unitDto);

    UnitDto toDto(Unit unit);
}
