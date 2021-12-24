package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.RetailOperationWithPointsDto;
import com.trade_accounting.services.impl.Stubs.model.RetailOperationWithPointsModelStubs;
import com.trade_accounting.utils.mapper.RetailOperationWithPointsMapper;
import org.mapstruct.factory.Mappers;

public class RetailOperationWithPointsDtoStubs {

    private static final RetailOperationWithPointsMapper mapper = Mappers.getMapper(RetailOperationWithPointsMapper.class);

    public static RetailOperationWithPointsDto getDto(Long id) {
        return mapper.toDto(RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(id));
    }
}
