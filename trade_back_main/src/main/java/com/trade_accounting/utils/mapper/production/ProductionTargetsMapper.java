package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.dto.production.ProductionTargetsDto;
import com.trade_accounting.models.entity.production.ProductionTargets;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductionTargetsMapper {
    //ProductionTargets

    @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "deliveryPlannedMoment", target = "deliveryPlannedMoment", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "materialWarehouseId", target = "materialWarehouse.id")
    @Mapping(source = "productionWarehouseId", target = "productionWarehouse.id")
    @Mapping(source = "productionStart", target = "productionStart", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "productionEnd", target = "productionEnd", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "employeeOwner", target = "employeeOwner")
    @Mapping(source = "updated", target = "updated", dateFormat = "yyyy-MM-dd HH:mm")
    ProductionTargets toModel(ProductionTargetsDto productionTargetsDto);


    @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "deliveryPlannedMoment", target = "deliveryPlannedMoment", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "materialWarehouse.id", target = "materialWarehouseId")
    @Mapping(source = "productionWarehouse.id", target = "productionWarehouseId")
    @Mapping(source = "productionStart", target = "productionStart", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "productionEnd", target = "productionEnd", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "updated", target = "updated", dateFormat = "yyyy-MM-dd HH:mm")
    ProductionTargetsDto toDto(ProductionTargets productionTargets);
}
