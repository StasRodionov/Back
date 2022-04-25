package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.SupplierAccountProductsListDto;
import com.trade_accounting.models.entity.warehouse.SupplierAccountProductsList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierAccountProductsListMapper {
    //SupplierAccountProductsList

    @Mapping(source = "supplierAccountId", target = "supplierAccount.id")
    @Mapping(source = "productId", target = "product.id")
    SupplierAccountProductsList toModel(SupplierAccountProductsListDto supplierAccountProductsListDto);


    @Mapping(source = "supplierAccount.id", target = "supplierAccountId")
    @Mapping(source = "product.id", target = "productId")
    SupplierAccountProductsListDto toDto(SupplierAccountProductsList supplierAccountProductsList);
}
