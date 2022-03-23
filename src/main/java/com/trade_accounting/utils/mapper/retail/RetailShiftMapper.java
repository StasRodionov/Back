package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailShiftDto;
import com.trade_accounting.models.entity.retail.RetailShift;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailShiftMapper {
    //RetailShift
    RetailShift toModel(RetailShiftDto retailShiftDto);

    RetailShiftDto toDto(RetailShift retailShift);
}
