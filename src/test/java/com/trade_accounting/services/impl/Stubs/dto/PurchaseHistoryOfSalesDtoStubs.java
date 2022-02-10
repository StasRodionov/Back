package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.PrepayoutDto;
import com.trade_accounting.models.dto.PurchaseHistoryOfSalesDto;
import com.trade_accounting.services.impl.Stubs.model.PurchaseHistoryOfSalesStubs;
import com.trade_accounting.utils.mapper.PurchaseHistoryOfSalesMapper;
import org.mapstruct.factory.Mappers;

public class PurchaseHistoryOfSalesDtoStubs {
    private static final PurchaseHistoryOfSalesMapper mapper = Mappers.getMapper(PurchaseHistoryOfSalesMapper.class);

    public static PurchaseHistoryOfSalesDto getDto(Long id) {
        return mapper.toDto(PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(id));
    }
}
