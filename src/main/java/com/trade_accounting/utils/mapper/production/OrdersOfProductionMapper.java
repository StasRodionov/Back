package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.dto.production.OrdersOfProductionDto;
import com.trade_accounting.models.entity.production.OrdersOfProduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersOfProductionMapper {

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "plannedProductionDate", source = "plannedProductionDate", dateFormat = "dd-MM-yyyy HH:mm")
    OrdersOfProduction toModel(OrdersOfProductionDto ordersOfProductionDto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "technicalCardId", source = "technicalCard.id")
    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "plannedProductionDate", source = "plannedProductionDate", dateFormat = "dd-MM-yyyy HH:mm")
    OrdersOfProductionDto toDto(OrdersOfProduction ordersOfProduction);
}

