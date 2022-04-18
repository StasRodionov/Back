package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BuyersReturnMapper {
    //BuyersReturn
    BuyersReturn toModel(BuyersReturnDto emp);

    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "companyId", source = "company.id")
    BuyersReturnDto toDto(BuyersReturn buyersReturn);
}
