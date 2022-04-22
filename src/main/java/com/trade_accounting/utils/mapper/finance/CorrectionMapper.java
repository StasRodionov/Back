package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.Correction;
import com.trade_accounting.models.dto.finance.CorrectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CorrectionMapper {
    //Correction
    @Mapping(target = "date", ignore = true)
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "companyId", target = "company.id")
    Correction toModel(CorrectionDto correctionDto);

    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "companyId", source = "company.id")
    CorrectionDto toDto(Correction correction);
}
