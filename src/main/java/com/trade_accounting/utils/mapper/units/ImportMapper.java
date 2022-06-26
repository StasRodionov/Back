package com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.dto.units.ImportDto;
import com.trade_accounting.models.entity.units.Import;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImportMapper {
    Import toModel(ImportDto importDto);
    ImportDto toDto(Import importEntity);
}
