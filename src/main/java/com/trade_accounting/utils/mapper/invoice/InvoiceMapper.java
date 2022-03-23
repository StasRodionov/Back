package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    //Invoice
    Invoice toModel(InvoiceDto emp);

    InvoiceDto toDto(Invoice invoice);
}
