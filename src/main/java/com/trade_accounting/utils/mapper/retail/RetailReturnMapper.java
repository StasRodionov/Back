package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailReturn;
import com.trade_accounting.models.dto.retail.RetailReturnDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailReturnMapper {
    //RetailReturn
    RetailReturn toModel(RetailReturnDto emp);

    RetailReturnDto toDto(RetailReturn retailReturn);
}
