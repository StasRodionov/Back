package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.retail.RetailStoreDto;
import com.trade_accounting.utils.mapper.util.EmployeeToLongMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EmployeeToLongMapper.class})
public interface RetailStoreMapper {
    //RetailStore
    @Mapping(target = "company.id", source = "companyId")
    @Mapping(target = "cashiers", ignore = true)
    RetailStore toModel(RetailStoreDto retailStoreDto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "cashiersIds", source = "cashiers")
    RetailStoreDto toDto(RetailStore retailStore);
}