package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.InventarizationProduct;
import com.trade_accounting.models.dto.warehouse.InventarizationProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventarizationProductMapper {
    //InventarizationProduct
    @Mapping(source = "productId", target = "product.id")
    InventarizationProduct toModel(InventarizationProductDto inventarizationProductDto);

    InventarizationProductDto toDto(InventarizationProduct inventarizationProduct);
}
