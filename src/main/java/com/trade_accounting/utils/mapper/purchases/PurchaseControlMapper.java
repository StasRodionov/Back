package com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.purchases.PurchaseControl;
import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;
import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import com.trade_accounting.models.entity.purchases.PurchaseHistoryOfSales;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface PurchaseControlMapper {
    //PurchaseControl
    @Mappings({
            @Mapping(source = "historyOfSalesId", target = "historyOfSales.id"),
            @Mapping(source = "currentBalanceId", target = "currentBalance.id"),
            @Mapping(source = "forecastId", target = "forecast.id")
    })
    PurchaseControl toModel(PurchaseControlDto purchaseControlDto);

    @Mappings({
            @Mapping(source = "historyOfSales.id", target = "historyOfSalesId"),
            @Mapping(source = "currentBalance.id", target = "currentBalanceId"),
            @Mapping(source = "forecast.id", target = "forecastId")
    })
    PurchaseControlDto toDto(PurchaseControl purchaseControl);
}
