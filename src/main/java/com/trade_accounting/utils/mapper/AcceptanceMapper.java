package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.dto.AcceptanceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AcceptanceMapper {
    // Acceptance
    @Mappings({
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "warehouse.id", target = "warehouseId"),
            @Mapping(source = "contract.id", target = "contractId"),
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "project.id", target = "projectId"),
            @Mapping(source = "employeeChanged.id", target = "employeeChangedId"),
    })
    AcceptanceDto toDto(Acceptance acceptance);

    Acceptance toModel(AcceptanceDto acceptance);
}
