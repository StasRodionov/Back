package com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.dto.units.ExportDto;
import com.trade_accounting.models.entity.units.Export;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExportMapper {

    Export toModel(ExportDto exportDto);

    ExportDto toDto(Export scenario);
}
