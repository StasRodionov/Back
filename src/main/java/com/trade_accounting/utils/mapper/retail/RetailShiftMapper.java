package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailShiftDto;
import com.trade_accounting.models.entity.retail.RetailShift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailShiftMapper {
    //RetailShift
    @Mapping(source = "retailStoreId", target = "retailStore.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "companyId", target = "company.id")
    RetailShift toModel(RetailShiftDto retailShiftDto);

    @Mapping(source = "retailStore.id", target = "retailStoreId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "company.id", target = "companyId")
    RetailShiftDto toDto(RetailShift retailShift);
}
