package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InvoiceReceived;
import com.trade_accounting.models.dto.InvoiceReceivedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvoiceReceivedMapper {
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "acceptance.id", target = "acceptanceId")
    })
    InvoiceReceivedDto toDto(InvoiceReceived invoiceReceived);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "acceptanceId", target = "acceptance.id")
    })
    InvoiceReceived toModel(InvoiceReceivedDto invoiceReceivedDto);

}
