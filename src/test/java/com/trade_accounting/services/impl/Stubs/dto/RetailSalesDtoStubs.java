package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.services.impl.Stubs.model.RetailSalesModelStubs;
import com.trade_accounting.utils.mapper.RetailSalesMapper;
import org.mapstruct.factory.Mappers;

public class RetailSalesDtoStubs {

    private static final RetailSalesMapper mapper = Mappers.getMapper(RetailSalesMapper.class);

    public static RetailSalesDto getDto(Long id) {
        return mapper.toDto(RetailSalesModelStubs.getRetailSales(id));
    }
}
