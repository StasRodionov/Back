package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.RetailCloudCheck;
import com.trade_accounting.models.dto.RetailCloudCheckDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RetailCloudCheckMapper {
    //RetailMaking
    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "initiator.id", target = "initiatorId"),
            @Mapping(source = "fiscalizationPoint.id", target = "fiscalizationPointId"),
            @Mapping(source = "currency.id", target = "currencyId"),
            @Mapping(source = "cashier.id", target = "cashierId")
    })
    RetailCloudCheckDto toDto(RetailCloudCheck retailCloudCheck);

    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "initiatorId", target = "initiator.id"),
            @Mapping(source = "fiscalizationPointId", target = "fiscalizationPoint.id"),
            @Mapping(source = "currencyId", target = "currency.id"),
            @Mapping(source = "cashierId", target = "cashier.id")
    })
    RetailCloudCheck toModel(RetailCloudCheckDto retailCloudCheckDto);
}
