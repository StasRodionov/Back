package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.InvoicesStatus;
import com.trade_accounting.models.dto.InvoicesStatusDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.InvoicesStatusMapper;
import org.mapstruct.factory.Mappers;

public class InvoicesStatusDtoStubs {
    private static final InvoicesStatusMapper invoicesStatusMapper = Mappers.getMapper(InvoicesStatusMapper.class);

    public static InvoicesStatusDto getInvoicesStatusDto(Long id) {
        return invoicesStatusMapper.toDto(ModelStubs.getInvoicesStatus(id));
    }
}
