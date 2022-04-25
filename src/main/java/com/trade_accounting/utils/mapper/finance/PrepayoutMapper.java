package com.trade_accounting.utils.mapper.finance;


import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.finance.Prepayout;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.finance.PrepayoutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PrepayoutMapper {
    //Prepayout
    @Mapping(source = "retailStoreId", target = "retailStore.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    Prepayout toModel(PrepayoutDto emp);


    @Mapping(source = "retailStore.id", target = "retailStoreId")
    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    PrepayoutDto toDto(Prepayout prepayout);
}
