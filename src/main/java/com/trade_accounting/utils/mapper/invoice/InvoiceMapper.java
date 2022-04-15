package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    //Invoice
    Invoice toModel(InvoiceDto emp);

    @Mapping(target = "companyId", source = "invoice.company.id")
    @Mapping(target = "contractorId", source = "invoice.contractor.id")
    @Mapping(target = "warehouseId", source = "invoice.warehouse.id")
    InvoiceDto toDto(Invoice invoice);
}
