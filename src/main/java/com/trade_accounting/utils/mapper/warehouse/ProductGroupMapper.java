package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import com.trade_accounting.models.entity.warehouse.ProductGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductGroupMapper {
    @Mapping(source = "parentId", target = "parent.id")
    ProductGroup toModel(ProductGroupDto productGroupDto);

    @Mapping(source = "parent.id", target = "parentId")
    ProductGroupDto toDto(ProductGroup productGroup);

}
