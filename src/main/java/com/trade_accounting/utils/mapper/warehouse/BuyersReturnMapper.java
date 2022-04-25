package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BuyersReturnMapper {
    //BuyersReturn
    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "companyId", target = "company.id")
    BuyersReturn toModel(BuyersReturnDto emp);

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "companyId", source = "company.id")
    BuyersReturnDto toDto(BuyersReturn buyersReturn);
}
