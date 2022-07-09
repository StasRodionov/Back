package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.InventarizationProduct;
import com.trade_accounting.models.dto.warehouse.InventarizationProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventarizationProductMapper {
    //InventarizationProduct
    InventarizationProduct toModel(InventarizationProductDto inventarizationProductDto);

    @Mapping(target = "productId", source = "product.id")
    InventarizationProductDto toDto(InventarizationProduct inventarizationProduct);
}
