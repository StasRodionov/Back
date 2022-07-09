package com.trade_accounting.utils.mapper.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfProductDto;
import com.trade_accounting.models.entity.warehouse.TypeOfProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeOfProductMapper {

    TypeOfProduct toModel(TypeOfProductDto typeOfProductDto);

    TypeOfProductDto toDto(TypeOfProduct typeOfProduct);
}
