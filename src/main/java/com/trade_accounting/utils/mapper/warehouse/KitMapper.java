package com.trade_accounting.utils.mapper.warehouse;


import com.trade_accounting.models.dto.warehouse.KitDto;
import com.trade_accounting.models.entity.warehouse.Kit;
import com.trade_accounting.utils.mapper.util.FileMapper;
import com.trade_accounting.utils.mapper.util.ImageMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ImageMapper.class, FileMapper.class, ProductMapper.class})
public interface KitMapper {

    @Mapping(source = "unitId", target = "unit.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "productPriceIds", target = "productPrices")
    @Mapping(source = "taxSystemId", target = "taxSystem.id")
    @Mapping(source = "imageDtos", target = "images")
    @Mapping(source = "fileDtos", target = "files")
    @Mapping(source = "productGroupId", target = "productGroup.id")
    @Mapping(source = "productDtos", target = "products")
    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "departmentId", target = "department.id")
    Kit toModel(KitDto kitDto);

    @Mapping(source = "unit.id", target = "unitId")
    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(source = "productPrices", target = "productPriceIds")
    @Mapping(source = "taxSystem.id", target = "taxSystemId")
    @Mapping(source = "images", target = "imageDtos")
    @Mapping(source = "files", target = "fileDtos")
    @Mapping(source = "productGroup.id", target = "productGroupId")
    @Mapping(source = "products", target = "productDtos")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "department.id", target = "departmentId")
    Kit toDto(KitDto kitDto);
}
