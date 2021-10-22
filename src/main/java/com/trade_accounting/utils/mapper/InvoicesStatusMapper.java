package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InvoicesStatus;
import com.trade_accounting.models.dto.InvoicesStatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoicesStatusMapper {

    default InvoicesStatusDto toDto(InvoicesStatus invoicesStatus) {
        InvoicesStatusDto invoicesStatusDto = new InvoicesStatusDto();

        if (invoicesStatus == null) {
            return null;
        } else {
            invoicesStatusDto.setId(invoicesStatus.getId());
            invoicesStatusDto.setStatusName(invoicesStatus.getStatusName());
            return invoicesStatusDto;
        }
    }

    default InvoicesStatus toModel(InvoicesStatusDto invoicesStatusDto) {
        if (invoicesStatusDto == null) {
            return null;
        }
        return InvoicesStatus.builder()
                .id(invoicesStatusDto.getId())
                .statusName(invoicesStatusDto.getStatusName())
                .build();
    }
}
