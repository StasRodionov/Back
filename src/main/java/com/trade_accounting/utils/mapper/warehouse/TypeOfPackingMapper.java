package com.trade_accounting.utils.mapper.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfPackingDto;
import com.trade_accounting.models.entity.warehouse.TypeOfPacking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeOfPackingMapper {

    TypeOfPacking toModel(TypeOfPackingDto typeOfPackingDto);

    TypeOfPackingDto toDto(TypeOfPacking typeOfPacking);
}
