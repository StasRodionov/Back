package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import com.trade_accounting.models.dto.finance.PrepaymentReturnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrepaymentReturnMapper {
    //PrepaymentReturn

    @Mapping(source = "retailStoreId", target = "retailStore.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "time", target = "time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PrepaymentReturn toModel(PrepaymentReturnDto prepaymentReturnDto);

    @Mapping(target = "retailStoreId", source = "retailStore.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(source = "time", target = "time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PrepaymentReturnDto toDto(PrepaymentReturn prepaymentReturn);
}
