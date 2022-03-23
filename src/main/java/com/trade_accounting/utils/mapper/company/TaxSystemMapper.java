package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.TaxSystem;
import com.trade_accounting.models.dto.company.TaxSystemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxSystemMapper {
    //TaxSystem
    TaxSystem toModel(TaxSystemDto taxSystemDto);

    TaxSystemDto toDto(TaxSystem taxSystem);
}
