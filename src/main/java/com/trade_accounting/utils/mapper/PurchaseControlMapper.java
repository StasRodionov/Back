package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.PurchaseControl;
import com.trade_accounting.models.dto.PurchaseControlDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseControlMapper {
    /**
     * @return PurchaseControl
     */
    @Mappings({
            @Mapping(source = "historyOfSalesId", target = "historyOfSales.id"),
            @Mapping(source = "currentBalanceId", target = "currentBalance.id"),
            @Mapping(source = "forecastId", target = "forecast.id")
    })
    PurchaseControl toModel(PurchaseControlDto purchaseControlDto);

    /**
     * @return PurchaseControlDto
     */
    @Mappings({
            @Mapping(source = "historyOfSales.id", target = "historyOfSalesId"),
            @Mapping(source = "currentBalance.id", target = "currentBalanceId"),
            @Mapping(source = "forecast.id", target = "forecastId")
    })
    PurchaseControlDto toDto(PurchaseControl purchaseControl);
}
