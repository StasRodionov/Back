package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.OrdersOfProduction;
import com.trade_accounting.models.dto.production.OrdersOfProductionDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface OrdersOfProductionMapper {
    //OrdersOfProduction
    OrdersOfProduction toModel(OrdersOfProductionDto ordersOfProductionDto);

    OrdersOfProductionDto toDto(OrdersOfProduction ordersOfProduction);
}

