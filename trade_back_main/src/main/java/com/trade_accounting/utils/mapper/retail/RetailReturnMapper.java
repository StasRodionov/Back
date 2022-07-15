package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailReturn;
import com.trade_accounting.models.dto.retail.RetailReturnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailReturnMapper {
    //RetailReturn

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "retailStoreId", target = "retailStore.id")
    RetailReturn toModel(RetailReturnDto emp);

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "retailStore.id", target = "retailStoreId")
    RetailReturnDto toDto(RetailReturn retailReturn);
}
