package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.OrdersOfProduction;
import com.trade_accounting.models.dto.OrdersOfProductionDto;

import java.util.List;

public interface OrdersOfProductionService extends AbstractService<OrdersOfProductionDto>, SearchableService<OrdersOfProduction, OrdersOfProductionDto>{

    List<OrdersOfProductionDto> search(String searchTerm);
}
