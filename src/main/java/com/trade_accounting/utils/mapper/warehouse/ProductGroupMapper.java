package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import com.trade_accounting.models.entity.warehouse.ProductGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductGroupMapper {
    //ProductGroup
    //На момент написания не известно работает или нет из-за рекурсии
    @Mapping(source = "parentId", target = "productGroup.id")
    ProductGroup toModel(ProductGroupDto productGroupDto);

    @Mapping(source = "productGroup.id", target = "parentId")
    ProductGroupDto toDto(ProductGroup productGroup);

}
