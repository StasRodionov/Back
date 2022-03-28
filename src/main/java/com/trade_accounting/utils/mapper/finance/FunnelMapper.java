package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.dto.finance.FunnelDto;
import com.trade_accounting.models.entity.finance.Funnel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FunnelMapper {
    //Funnel
    Funnel toModel(FunnelDto emp);

    FunnelDto toDto(Funnel funnel);
}
