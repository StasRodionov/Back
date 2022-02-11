package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailMaking;
import com.trade_accounting.models.dto.retail.RetailMakingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RetailMakingMapper {
    //RetailMaking
    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "retailStore.id", target = "retailStoreId"),
            @Mapping(source = "company.id", target = "companyId")
    })
    RetailMakingDto toDto(RetailMaking retailMaking);

    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "retailStoreId", target = "retailStore.id"),
            @Mapping(source = "companyId", target = "company.id")
    })
    RetailMaking toModel(RetailMakingDto retailMakingDto);
}
