package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.dto.warehouse.AcceptanceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AcceptanceMapper {
    //Acceptance
    Acceptance toModel(AcceptanceDto acceptance);

    AcceptanceDto toDto(Acceptance acceptance);
}