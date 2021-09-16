package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.IssuedInvoice;
import com.trade_accounting.models.dto.IssuedInvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IssuedInvoiceMapper {
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId")
    })
    IssuedInvoiceDto toDto(IssuedInvoice issuedInvoice);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id")
    })
    IssuedInvoice toModel(IssuedInvoiceDto issuedInvoiceDto);
}
