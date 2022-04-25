package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.models.entity.retail.RetailMaking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailMakingMapper {
    //RetailMaking
    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    RetailMaking toModel(RetailMakingDto retailMakingDto);

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "retailStore.id", target = "retailStoreId")
    @Mapping(source = "company.id", target = "companyId")
    RetailMakingDto toDto(RetailMaking retailMaking);
}
