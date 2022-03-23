package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.retail.RetailStoreDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailStoreMapper {
    //RetailStore
    RetailStore toModel(RetailStoreDto retailStoreDto);

    RetailStoreDto toDto(RetailStore retailStore);
}