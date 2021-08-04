package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.PriceListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Andrey Melnikov
 */

@Mapper(componentModel = "spring")
public interface PriceListMapper {

    /**
     * @return PriceListDto
     */
    @Mapping(source = "company.id", target = "companyId")
    default PriceListDto toDto(PriceList priceList){
        PriceListDto priceListDto = new PriceListDto();
        if (priceList == null){
            return null;
        } else {
            priceListDto.setId(priceList.getId());
            priceListDto.setNumber(priceList.getNumber());
            priceListDto.setTime(priceList.getTime());
            priceListDto.setSent(priceList.getSent());
            priceListDto.setPrinted(priceList.getPrinted());
            priceListDto.setCommentary(priceList.getCommentary());

        }
        return priceListDto;
    }

    /**
     * @return PriceList
     */
    @Mapping(source = "companyId", target = "company.id")
    default PriceList toModel(PriceListDto priceListDto){
        if (priceListDto == null) {
            return null;
        }
        return PriceList.builder()
                .id(priceListDto.getId())
                .number(priceListDto.getNumber())
                .time(priceListDto.getTime())
                .sent(priceListDto.getSent())
                .printed(priceListDto.getPrinted())
                .commentary(priceListDto.getCommentary())
                .build();
    }
}
