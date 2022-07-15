package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.dto.warehouse.AcceptanceDto;
import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AcceptanceMapper {

    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "contractId", target = "contract.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "employeeChangedId", target = "employeeChanged.id")
    @Mapping(source = "projectId", target = "project.id")
    Acceptance toModel(AcceptanceDto acceptance);

    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "contractId", source = "contract.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "employeeChangedId", source = "employeeChanged.id")
    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "acceptanceProductIds", source = "acceptanceProduction")
    AcceptanceDto toDto(Acceptance acceptance);

    default Long acceptProductionToProductIds(AcceptanceProduction acceptanceProduction) {
        return acceptanceProduction.getProduct().getId();
    }
}