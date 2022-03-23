package com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.models.entity.purchases.PurchaseHistoryOfSales;
import com.trade_accounting.models.dto.purchases.PurchaseHistoryOfSalesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseHistoryOfSalesMapper {
    //PurchaseHistoryOfSales
    PurchaseHistoryOfSales toModel(PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto);

    PurchaseHistoryOfSalesDto toDto(PurchaseHistoryOfSales purchaseHistoryOfSales);
}
