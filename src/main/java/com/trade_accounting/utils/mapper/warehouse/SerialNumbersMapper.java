package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.SerialNumbersDto;
import com.trade_accounting.models.entity.warehouse.SerialNumbers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SerialNumbersMapper {
    //SerialNumbersMapper
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(target = "code", source = "code"),
            @Mapping(target = "vendorCode", source = "vendorCode"),
            @Mapping(target = "product.id", source = "productId"),
            @Mapping(target = "warehouse.id", source = "warehouseId"),
            @Mapping(target = "typeDocument", source = "typeDocument"),
            @Mapping(target = "documentNumber", source = "documentNumber"),
            @Mapping(target = "description", source = "description"),

    })
    SerialNumbers toModel(SerialNumbersDto serialNumbersDto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(target = "code", source = "code"),
            @Mapping(target = "vendorCode", source = "vendorCode"),
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "warehouseId", source = "warehouse.id"),
            @Mapping(target = "typeDocument", source = "typeDocument"),
            @Mapping(target = "documentNumber", source = "documentNumber"),
            @Mapping(target = "description", source = "description"),

    })
    SerialNumbersDto toDto(SerialNumbers serialNumbers);
}
