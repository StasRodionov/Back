package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InvoiceToBuyerListProducts;
import com.trade_accounting.models.dto.InvoiceToBuyerListProductsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvoiceToBuyerListProductsMapper {

    @Mappings({
            @Mapping(source = "supplierAccount.id", target = "supplierAccountId"),
            @Mapping(source = "product.id", target = "productId")
    })
    InvoiceToBuyerListProductsDto toDto(InvoiceToBuyerListProducts invoiceToBuyerListProducts);

    @Mappings({
            @Mapping(source = "supplierAccountId", target = "supplierAccount.id"),
            @Mapping(source = "productId", target = "product.id")
    })
    InvoiceToBuyerListProducts toModel(InvoiceToBuyerListProductsDto invoiceToBuyerListProductsDto);
}
