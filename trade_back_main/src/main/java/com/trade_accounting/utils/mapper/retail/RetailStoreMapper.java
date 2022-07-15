package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.retail.RetailStoreDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailStoreMapper {
    //RetailStore
    @Mapping(target = "company.id", source = "companyId")
    RetailStore toModel(RetailStoreDto retailStoreDto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "cashiersIds", source = "cashiers")
    RetailStoreDto toDto(RetailStore retailStore);

    default Long employeeToLong(Employee employee) {
        return employee.getId();
    }
}