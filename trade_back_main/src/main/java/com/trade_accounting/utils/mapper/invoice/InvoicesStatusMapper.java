package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoicesStatusMapper {
    //InvoicesStatus
    InvoicesStatus toModel(InvoicesStatusDto invoicesStatusDto);

    InvoicesStatusDto toDto(InvoicesStatus invoicesStatus);
}
