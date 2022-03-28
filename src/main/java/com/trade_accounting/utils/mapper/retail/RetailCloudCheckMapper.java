package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.retail.RetailCloudCheck;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.entity.units.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailCloudCheckMapper {
    //RetailCloudCheck
    RetailCloudCheck toModel(RetailCloudCheckDto retailCloudCheckDto);

    RetailCloudCheckDto toDto(RetailCloudCheck retailCloudCheck);
}

