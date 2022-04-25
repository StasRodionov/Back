package com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.entity.purchases.PurchaseControl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseControlMapper {
    //PurchaseControl

    @Mapping(source = "productNameId", target = "product.id")
    @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "historyOfSalesId", target = "historyOfSales.id")
    @Mapping(source = "currentBalanceId", target = "currentBalance.id")
    @Mapping(source = "forecastId", target = "forecast.id")
    PurchaseControl toModel(PurchaseControlDto purchaseControlDto);

    @Mapping(target = "productNameId", source = "product.id")
    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(source = "historyOfSales.id", target = "historyOfSalesId")
    @Mapping(source = "currentBalance.id", target = "currentBalanceId")
    @Mapping(source = "forecast.id", target = "forecastId")
    PurchaseControlDto toDto(PurchaseControl purchaseControl);
}
