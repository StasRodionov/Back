package com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.dto.units.SalesChannelDto;
import com.trade_accounting.models.entity.units.SalesChannel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesChannelMapper {

    SalesChannel toModel(SalesChannelDto salesChannelDto);

    SalesChannelDto toDto(SalesChannel salesChannel);

}
