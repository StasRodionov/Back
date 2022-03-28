package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.ProductionTargets;
import com.trade_accounting.models.dto.production.ProductionTargetsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductionTargetsMapper {
    //ProductionTargets
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "deliveryPlannedMoment", target = "deliveryPlannedMoment", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "materialWarehouse", target = "materialWarehouse.id"),
            @Mapping(source = "productionWarehouse", target = "productionWarehouse.id"),
            @Mapping(source = "productionStart", target = "productionStart", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "productionEnd", target = "productionEnd", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "shared", target = "shared"),
            @Mapping(source = "owner", target = "Owner"),
            @Mapping(source = "employeeOwner", target = "employeeOwner"),
            @Mapping(source = "published", target = "published"),
            @Mapping(source = "printed", target = "printed"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "updated", target = "updated", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "updatedByName", target = "updatedByName"),
    })
    ProductionTargets toModel(ProductionTargetsDto productionTargetsDto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "deliveryPlannedMoment", target = "deliveryPlannedMoment", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "materialWarehouse.id", target = "materialWarehouse"),
            @Mapping(source = "productionWarehouse.id", target = "productionWarehouse"),
            @Mapping(source = "productionStart", target = "productionStart", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "productionEnd", target = "productionEnd", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "shared", target = "shared"),
            @Mapping(source = "owner", target = "Owner"),
            @Mapping(source = "employeeOwner", target = "employeeOwner"),
            @Mapping(source = "published", target = "published"),
            @Mapping(source = "printed", target = "printed"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "updated", target = "updated", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "updatedByName", target = "updatedByName"),
    })
    ProductionTargetsDto toDto(ProductionTargets productionTargets);
}
