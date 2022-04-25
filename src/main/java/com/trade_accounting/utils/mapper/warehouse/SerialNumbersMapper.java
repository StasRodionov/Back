package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.SerialNumbersDto;
import com.trade_accounting.models.entity.warehouse.SerialNumbers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SerialNumbersMapper {
    //SerialNumbersMapper

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "product.description", source = "description")
    @Mapping(target = "warehouse.id", source = "warehouseId")
    SerialNumbers toModel(SerialNumbersDto serialNumbersDto);


    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.description", target = "description")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    SerialNumbersDto toDto(SerialNumbers serialNumbers);
}
