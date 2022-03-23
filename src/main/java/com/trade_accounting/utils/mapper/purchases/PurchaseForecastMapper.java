package com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import com.trade_accounting.models.dto.purchases.PurchaseForecastDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseForecastMapper {
    //PurchaseForecast
    PurchaseForecast toModel(PurchaseForecastDto purchaseForecastDto);

    PurchaseForecastDto toDto(PurchaseForecast purchaseForecast);
}
