package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.Payout;
import com.trade_accounting.models.dto.finance.PayoutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PayoutMapper {
    //Payout

    @Mapping(source = "retailStoreId", target = "retailStore.id")
    @Mapping(source = "companyId", target = "company.id")
//    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    Payout toModel(PayoutDto payoutsDto);

    @Mapping(source = "retailStore.id", target = "retailStoreId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    PayoutDto toDto(Payout payout);

}

