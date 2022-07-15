package com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.dto.units.OnlineStoreDto;
import com.trade_accounting.models.entity.units.OnlineStore;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OnlineStoreMapper {
    OnlineStoreDto toDto(OnlineStore onlineStore);
    OnlineStore toEntity(OnlineStoreDto onlineStoreDto);
}
