package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.PrepaymentReturn;
import com.trade_accounting.models.dto.PrepaymentReturnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PrepaymentReturnMapper {

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "retailStoreId", target = "retailStore.id"),
    })
    PrepaymentReturn toModel(PrepaymentReturnDto prepaymentReturnDto);

    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "retailStore.id", target = "retailStoreId"),
            @Mapping(source = "time", target = "time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    PrepaymentReturnDto toDto(PrepaymentReturn prepaymentReturn);
}
