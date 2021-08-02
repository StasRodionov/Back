package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.dto.InvoiceProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvoiceProductMapper {
    //InvoiceProduct
    @Mappings({
            @Mapping(source = "invoice", target = "invoiceDto"),
            @Mapping(source = "product", target = "productDto")
    })
    InvoiceProductDto toDto(InvoiceProduct invoiceProduct);

    @Mappings({
            @Mapping(source = "invoiceDto", target = "invoice"),
            @Mapping(source = "productDto", target = "product")
    })
    InvoiceProduct toModel(InvoiceProductDto invoiceProductDto);
}
