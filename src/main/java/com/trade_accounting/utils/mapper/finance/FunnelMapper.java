package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.dto.finance.FunnelDto;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.entity.finance.Funnel;
import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FunnelMapper {
    default FunnelDto toDto(Funnel funnel) {
        if (funnel == null) {
            return null;
        }
        FunnelDto.FunnelDtoBuilder funnelDtoBuilder = FunnelDto.builder()
                .id(funnel.getId())
                .statusName(funnel.getStatusName())
                .count(funnel.getCount())
                .time(funnel.getTime())
                .conversion(funnel.getConversion())
                .price(funnel.getPrice());
        return funnelDtoBuilder.build();
    }


    default Funnel toModel(FunnelDto emp) {
        if (emp == null) {
            return null;
        }
        Funnel.FunnelBuilder funnelBuilder = Funnel.builder()
                .id(emp.getId())
                .statusName(emp.getStatusName())
                .time(emp.getTime())
                .count(emp.getCount())
                .conversion(emp.getConversion())
                .price(emp.getPrice());
        return funnelBuilder.build();
    }
}
