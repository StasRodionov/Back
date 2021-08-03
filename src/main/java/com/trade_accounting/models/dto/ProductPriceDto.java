package com.trade_accounting.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceDto {
    private Long id;
    private String typeOfPriceDto;
    private BigDecimal value;

//    public ProductPriceDto(Long id, Long typeOfPriceId, BigDecimal value) {
//        this.id = id;
//        this.value = value;
//        this.typeOfPriceDto = new TypeOfPriceDto();
//        typeOfPriceDto.setId(typeOfPriceId);
//    }
}
