package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.ReturnFromBuyers;
import com.trade_accounting.models.dto.ReturnFromBuyersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReturnFromBuyersMapper {
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contract.id", target = "contractId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "warehouse.id", target = "warehouseId")
    })
    ReturnFromBuyersDto toDto(ReturnFromBuyers returnFromBuyers);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractId", target = "contract.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "warehouseId", target = "warehouse.id"),
    })
    ReturnFromBuyers toModel(ReturnFromBuyersDto returnFromBuyersDto);
}
