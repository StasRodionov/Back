package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mappings({
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "contractDto", target = "contract"),
            @Mapping(source = "projectDto", target = "project"),
            @Mapping(target = "time", ignore = true)
    })
    Payment toModel(PaymentDto paymentDto);

    @Mappings({
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "contract", target = "contractDto"),
            @Mapping(source = "project", target = "projectDto"),
            @Mapping(source = "time", target = "time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    PaymentDto toDto(Payment payment);
}
