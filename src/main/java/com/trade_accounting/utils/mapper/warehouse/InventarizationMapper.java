package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Inventarization;
import com.trade_accounting.models.dto.warehouse.InventarizationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventarizationMapper {
    //Inventarization
    @Mapping(target = "date", ignore = true)
    Inventarization toModel(InventarizationDto inventarizationDto);

    InventarizationDto toDto(Inventarization inventarization);
}
