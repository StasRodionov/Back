package com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;
import com.trade_accounting.models.dto.purchases.PurchaseCurrentBalanceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseCurrentBalanceMapper {
    //PurchaseCurrentBalance
    PurchaseCurrentBalance toModel(PurchaseCurrentBalanceDto purchaseCurrentBalanceDto);

    PurchaseCurrentBalanceDto toDto(PurchaseCurrentBalance purchaseCurrentBalance);
}
