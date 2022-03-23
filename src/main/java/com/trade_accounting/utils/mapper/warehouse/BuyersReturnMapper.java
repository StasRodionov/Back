package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyersReturnMapper {
    //BuyersReturn
    BuyersReturn toModel(BuyersReturnDto emp);

    BuyersReturnDto toDto(BuyersReturn buyersReturn);
}
