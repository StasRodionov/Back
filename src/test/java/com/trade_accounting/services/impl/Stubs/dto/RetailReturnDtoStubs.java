package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.RetailReturn;
import com.trade_accounting.models.dto.RetailReturnDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.services.impl.Stubs.model.RetailReturnModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.RetailReturnMapper;
import com.trade_accounting.utils.mapper.RetailStoreMapper;
import org.mapstruct.factory.Mappers;

public class RetailReturnDtoStubs {

    private static final RetailReturnMapper mapper = Mappers.getMapper(RetailReturnMapper.class);

    public static RetailReturnDto getDto(Long id) {
        return mapper.toDto(RetailReturnModelStubs.getRetailReturn(id));
    }
}
