package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.models.entity.warehouse.Product;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Melnikov and Pavel Andrusov
 * @since 05.08.2021
 */
@Mapper(componentModel = "spring")
public interface PriceListMapper {

    default PriceListDto toDto(PriceList priceList) {
        if (priceList == null) {
            return null;
        }

        return PriceListDto.builder()
                .companyId(priceList.getCompany().getId())
                .id(priceList.getId())
                .number(priceList.getNumber())
                .time(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(priceList.getTime()))
                .sent(priceList.getSent())
                .printed(priceList.getPrinted())
                .commentary(priceList.getCommentary())
                .productsIds(productIds(priceList))
                .build();
    }

    default PriceList toModel(PriceListDto priceListDto) {
        if (priceListDto == null) {
            return null;
        }

        return PriceList.builder()
                .id(priceListDto.getId())
                .number(priceListDto.getNumber())
                .sent(priceListDto.getSent())
                .printed(priceListDto.getPrinted())
                .commentary(priceListDto.getCommentary())
                .build();
    }

    private List<Long> productIds(PriceList priceList) {
        if (priceList == null) {
            return Collections.emptyList();
        } else if (priceList.getProducts() == null) {
            return Collections.emptyList();
        } else {
            return priceList.getProducts().stream().map(Product::getId).collect(Collectors.toList());
        }
    }
}
