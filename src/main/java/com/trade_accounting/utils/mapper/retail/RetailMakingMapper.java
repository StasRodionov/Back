package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.models.entity.retail.RetailMaking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailMakingMapper {
    //RetailMaking
    RetailMaking toModel(RetailMakingDto retailMakingDto);

    RetailMakingDto toDto(RetailMaking retailMaking);
}
