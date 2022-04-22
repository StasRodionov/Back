package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    //Invoice
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "invoicesStatusId", target = "invoicesStatus.id")
    @Mapping(source = "invoiceProductsIds", target = "invoiceProducts")
    Invoice toModel(InvoiceDto emp);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "invoicesStatusId", source = "invoicesStatus.id")
    @Mapping(target = "invoiceProductsIds", source = "invoiceProducts")
    InvoiceDto toDto(Invoice invoice);

    default Long invoiceProductToLong(InvoiceProduct invoiceProduct) {
        return invoiceProduct.getId();
    }

    default InvoiceProduct longToInvoiceProduct(Long id) {
        return InvoiceProduct.builder()
                .id(id)
                .build();
    }
}

