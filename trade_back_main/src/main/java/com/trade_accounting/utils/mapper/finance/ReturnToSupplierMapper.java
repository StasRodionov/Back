package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.entity.finance.ReturnToSupplier;
import com.trade_accounting.models.dto.finance.ReturnToSupplierDto;
import com.trade_accounting.models.entity.util.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ReturnToSupplierMapper {
    //ReturnToSupplier
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "contractId", target = "contract.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "returnToSupplierDto", target = "project", qualifiedByName = "projectConverter")
    ReturnToSupplier toModel(ReturnToSupplierDto returnToSupplierDto);

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "contract.id", target = "contractId")
    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "project.id", target = "projectId")
    ReturnToSupplierDto toDto(ReturnToSupplier returnToSupplier);

    @Named("projectConverter")
    default Project projectFieldFromDtoToModel(ReturnToSupplierDto returnToSupplierDto) {
        return returnToSupplierDto.getProjectId() == null ? null :
                new Project(returnToSupplierDto.getProjectId(), null, null, null);
    }
}
