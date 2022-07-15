package com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    //Unit
    @Mapping(source = "dateOfChange", target = "dateOfChange", dateFormat = "dd-MM-yyyy HH:mm")
    Unit toModel(UnitDto unitDto);

    @Mapping(source = "dateOfChange", target = "dateOfChange", dateFormat = "dd-MM-yyyy HH:mm")
    UnitDto toDto(Unit unit);
}
