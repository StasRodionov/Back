package com.trade_accounting.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private BigDecimal weight;

    private BigDecimal volume;

    private BigDecimal purchasePrice;

    private String description;

    private UnitDto unitDto;

    //    private ContractorDto contractorDto; Пока нет реализации

    private TypeOfPriceDto typeOfPriceDto;

    private TaxSystemDto taxSystemDto;

    private ImageDto imageDto;

    private ProductGroupDto productGroupDto;

    private AttributeOfCalculationObjectDto attributeOfCalculationObjectDto;

}
