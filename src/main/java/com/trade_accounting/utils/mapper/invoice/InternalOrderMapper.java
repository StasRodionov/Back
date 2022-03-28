package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrder;
import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import com.trade_accounting.models.dto.invoice.InternalOrderDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * @author Pavel Andrusov
 */

@Mapper(componentModel = "spring")
public interface InternalOrderMapper {
    //InternalOrder
    InternalOrder toModel(InternalOrderDto internalOrderDto);

    InternalOrderDto toDto(InternalOrder internalOrder);
}
