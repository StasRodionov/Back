package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.dto.InternalOrderDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


/**
 * @author Pavel Andrusov
 * @version 1.0.0
 */

@Mapper(componentModel = "spring")
public interface InternalOrderMapper {

    /**
     * @return InternalOrderDto
     */
    default InternalOrderDto toDto(InternalOrder internalOrder) {
        InternalOrderDto internalOrderDto = new InternalOrderDto();
        if (internalOrder == null) {
            return null;
        } else {
            internalOrderDto.setId(internalOrder.getId());
            internalOrderDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(internalOrder.getDate()));
            internalOrderDto.setIsSent(internalOrder.getIsSent());
            internalOrderDto.setIsPrint(internalOrder.getIsPrint());
            internalOrderDto.setComment(internalOrder.getComment());
            internalOrderDto.setInternalOrderProductsIds(
                    internalOrder.getInternalOrderProducts().stream()
                            .map(InternalOrderProduct::getId)
                            .collect(Collectors.toList())
            );

            if (internalOrder.getCompany() == null) {
                return null;
            } else {
                internalOrderDto.setCompanyId(internalOrder.getCompany().getId());
                return internalOrderDto;
            }
        }
    }

    /**
     * @return InternalOrder
     */
    default InternalOrder toModel(InternalOrderDto internalOrderDto) {
        InternalOrder internalOrder = new InternalOrder();
        if (internalOrderDto == null) {
            return null;
        }

        internalOrder.setId(internalOrderDto.getId());
        internalOrder.setIsPrint(internalOrderDto.getIsPrint());
        internalOrder.setIsSent(internalOrderDto.getIsSent());
        internalOrder.setComment(internalOrderDto.getComment());

        return internalOrder;
    }
}
