package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailSalesDto;
import com.trade_accounting.models.entity.retail.RetailSales;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailSalesMapper {
    //RetailSales
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "retailStoreId", target = "retailStore.id")
    RetailSales toModel(RetailSalesDto retailSalesDto);

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(source = "retailStore.id", target = "retailStoreId")
    RetailSalesDto toDto(RetailSales retailSales);
}
