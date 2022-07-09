package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrder;
import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import com.trade_accounting.models.dto.invoice.InternalOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Pavel Andrusov
 */

@Mapper(componentModel = "spring")
public interface InternalOrderMapper {
    //InternalOrder
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "internalOrderProductsIds", target = "internalOrderProducts")
    InternalOrder toModel(InternalOrderDto internalOrderDto);


    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "internalOrderProductsIds", source = "internalOrderProducts")
    InternalOrderDto toDto(InternalOrder internalOrder);

    default Long internalOrderProductToLong(InternalOrderProduct internalOrderProduct) {
        return internalOrderProduct.getId();
    }

    default InternalOrderProduct longToInternalOrderProduct(Long id) {
        return InternalOrderProduct.builder()
                .id(id)
                .build();
    }
}
