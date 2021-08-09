package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.MoneySubProfitLoss;
import com.trade_accounting.models.dto.MoneySubProfitLossDto;

import java.util.List;

public interface MoneySubProfitLossService extends AbstractService<MoneySubProfitLossDto>,
        SearchableService<MoneySubProfitLoss, MoneySubProfitLossDto> {
    List<MoneySubProfitLossDto> getAll();
}