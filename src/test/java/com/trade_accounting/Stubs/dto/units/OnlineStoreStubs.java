package com.trade_accounting.Stubs.dto.units;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.units.OnlineStoreDto;
import com.trade_accounting.utils.mapper.units.OnlineStoreMapper;
import org.mapstruct.factory.Mappers;

public class OnlineStoreStubs {
    private static final OnlineStoreMapper onlineStoreMapper = Mappers.getMapper(OnlineStoreMapper.class);
    public static OnlineStoreDto getOnlineStoreDto(Long id) {
        return onlineStoreMapper.toDto(ModelStubs.getOnlineStore(id));
    }

}
