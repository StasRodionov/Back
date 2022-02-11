package com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    //Unit
    default Unit toModel(UnitDto unitDto) {
        if (unitDto == null) {
            return null;
        }

        return Unit.builder()
                .id(unitDto.getId())
                .fullName(unitDto.getFullName())
                .sortNumber(unitDto.getSortNumber())
                .shortName(unitDto.getShortName())
                .build();
    }

   default UnitDto toDto(Unit unit){
        UnitDto unitDto = new UnitDto();
        if (unit==null){
            return null;
        } else {
            unitDto.setId(unit.getId());
            unitDto.setFullName(unit.getFullName());
            unitDto.setShortName(unit.getShortName());
            unitDto.setSortNumber(unit.getSortNumber());
            return unitDto;
        }

    }
}
