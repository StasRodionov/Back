package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.dto.PriceListDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Andrey Melnikov
 */
@Mapper(componentModel = "spring")
public interface PriceListMapper {

    @Mapping(source = "company.id", target = "companyId")
    default PriceListDto toDto(PriceList priceList) {
        if (priceList == null) {
            return null;
        }
        PriceListDto priceListDto = new PriceListDto();

        priceListDto.setId(priceList.getId());
        priceListDto.setNumber(priceList.getNumber());
        priceListDto.setTime(priceList.getTime());
        priceListDto.setSent(priceList.getSent());
        priceListDto.setPrinted(priceList.getPrinted());
        priceListDto.setCommentary(priceList.getCommentary());

        return priceListDto;
    }

    @InheritInverseConfiguration
    default PriceList toModel(PriceListDto priceListDto) {
        if (priceListDto == null) {
            return null;
        }

        PriceList priceList = new PriceList();

        priceList.setId(priceListDto.getId());
        priceList.setNumber(priceListDto.getNumber());
        priceList.setTime(priceListDto.getTime());
        priceList.setSent(priceListDto.getSent());
        priceList.setPrinted(priceListDto.getPrinted());
        priceList.setCommentary(priceListDto.getCommentary());

        return priceList;
    }
}
