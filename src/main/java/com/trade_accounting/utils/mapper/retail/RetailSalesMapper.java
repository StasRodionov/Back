package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.retail.RetailSales;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.retail.RetailSalesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RetailSalesMapper {
    //RetailSales
    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "retailStoreId", target = "retailStore.id")

    })
    RetailSales toModel(RetailSalesDto retailSalesDto);

    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "retailStore.id", target = "retailStoreId")
    })
    RetailSalesDto toDto(RetailSales retailSales);
}
