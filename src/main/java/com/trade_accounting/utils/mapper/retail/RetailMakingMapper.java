package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.models.entity.retail.RetailMaking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailMakingMapper {
    //RetailMaking
    @Mapping(source = "retailStoreId", target = "retailStore.id")
    @Mapping(source = "companyId", target = "company.id")
    RetailMaking toModel(RetailMakingDto retailMakingDto);

    @Mapping(source = "retailStore.id", target = "retailStoreId")
    @Mapping(source = "company.id", target = "companyId")
    RetailMakingDto toDto(RetailMaking retailMaking);
}
