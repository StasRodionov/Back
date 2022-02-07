package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.RevenueDto;
import com.trade_accounting.models.dto.ShipmentProductDto;
import com.trade_accounting.services.impl.Stubs.model.RevenueModelStubs;
import com.trade_accounting.services.impl.Stubs.model.ShipmentProductModelStubs;
import com.trade_accounting.utils.mapper.RevenueMapper;
import com.trade_accounting.utils.mapper.ShipmentProductMapper;
import org.mapstruct.factory.Mappers;

public class RevenueDtoStubs {

    private static final RevenueMapper mapper = Mappers.getMapper(RevenueMapper.class);

    public static RevenueDto getDto(Long id) {
        return mapper.toDto(RevenueModelStubs.getRevenue(id));
    }
}
