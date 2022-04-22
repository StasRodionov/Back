package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.models.entity.retail.RetailCloudCheck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailCloudCheckMapper {
    //RetailCloudCheck

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "initiatorId", target = "initiator.id")
    @Mapping(source = "fiscalizationPointId", target = "fiscalizationPoint.id")
    @Mapping(source = "currencyId", target = "currency.id")
    RetailCloudCheck toModel(RetailCloudCheckDto retailCloudCheckDto);

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "initiator.id", target = "initiatorId")
    @Mapping(source = "fiscalizationPoint.id", target = "fiscalizationPointId")
    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "cashier.id", target = "cashierId")
    RetailCloudCheckDto toDto(RetailCloudCheck retailCloudCheck);
}

