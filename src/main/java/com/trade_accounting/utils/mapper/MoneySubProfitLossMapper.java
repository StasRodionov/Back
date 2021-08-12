package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.MoneySubProfitLoss;
import com.trade_accounting.models.dto.MoneySubProfitLossDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneySubProfitLossMapper {

    MoneySubProfitLossDto moneySubProfitLossToDTO(MoneySubProfitLoss moneySubProfitLoss);
}