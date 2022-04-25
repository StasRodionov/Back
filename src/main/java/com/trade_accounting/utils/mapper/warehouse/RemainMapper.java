package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.RemainDto;
import com.trade_accounting.models.entity.warehouse.Remain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RemainMapper {
    //Remain
    Remain toModel(RemainDto remainDto);

    @Mapping(source = "unit.id", target = "unitId")
    RemainDto toDto(Remain remain);
}
