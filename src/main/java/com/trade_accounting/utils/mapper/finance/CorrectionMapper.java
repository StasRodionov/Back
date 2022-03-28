package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.Correction;
import com.trade_accounting.models.dto.finance.CorrectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CorrectionMapper {
    //Correction
    @Mapping(target = "date", ignore = true)
    Correction toModel(CorrectionDto correctionDto);

    @Mapping(target = "date", ignore = true)
    CorrectionDto toDto(Correction correction);
}
