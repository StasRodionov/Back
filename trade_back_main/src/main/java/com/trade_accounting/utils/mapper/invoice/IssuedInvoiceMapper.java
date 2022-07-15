package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.IssuedInvoice;
import com.trade_accounting.models.dto.invoice.IssuedInvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IssuedInvoiceMapper {
    //IssuedInvoice
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "paymentId", target = "payment.id")
    IssuedInvoice toModel(IssuedInvoiceDto issuedInvoiceDto);


    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(source = "payment.id", target = "paymentId")
    IssuedInvoiceDto toDto(IssuedInvoice issuedInvoice);
}
