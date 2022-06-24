package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import com.trade_accounting.models.entity.warehouse.ProductGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductGroupMapper {
    @Mapping(source = "parentId", target = "parent.id")
    @Mapping(source = "taxSystemId", target = "taxSystem.id")
    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "departmentId", target = "department.id")
    ProductGroup toModel(ProductGroupDto productGroupDto);

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "taxSystem.id", target = "taxSystemId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "department.id", target = "departmentId")
    ProductGroupDto toDto(ProductGroup productGroup);

}
