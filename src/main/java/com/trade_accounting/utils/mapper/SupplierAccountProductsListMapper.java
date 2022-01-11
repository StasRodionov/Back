package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.SupplierAccountProductsList;
import com.trade_accounting.models.dto.SupplierAccountProductsListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SupplierAccountProductsListMapper {

    @Mappings({
            @Mapping(source = "supplierAccount.id", target = "supplierAccountId"),
            @Mapping(source = "product.id", target = "productId")
    })
    SupplierAccountProductsListDto toDto(SupplierAccountProductsList supplierAccountProductsList);

    @Mappings({
            @Mapping(source = "supplierAccountId", target = "supplierAccount.id"),
            @Mapping(source = "productId", target = "product.id")
    })
    SupplierAccountProductsList toModel(SupplierAccountProductsListDto supplierAccountProductsListDto);
}
