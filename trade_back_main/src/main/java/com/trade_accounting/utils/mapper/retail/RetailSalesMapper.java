package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailSalesDto;
import com.trade_accounting.models.entity.retail.RetailSales;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailSalesMapper {
    //RetailSales

    @Mapping(source = "time", target = "time", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "retailStoreId", target = "retailStore.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "companyId", target = "company.id")
    RetailSales toModel(RetailSalesDto retailSalesDto);

    @Mapping(source = "time", target = "time", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "retailStore.id", target = "retailStoreId")
    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(source = "company.id", target = "companyId")
    RetailSalesDto toDto(RetailSales retailSales);
}
