package com.trade_accounting.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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

    private Boolean archive = false;

    private ContractorDto contractorDto;

    private List<ProductPriceDto> productPriceDtos;

    private TaxSystemDto taxSystemDto;

    private List<ImageDto> imageDtos;

    private ProductGroupDto productGroupDto;

    private AttributeOfCalculationObjectDto attributeOfCalculationObjectDto;

    public ProductDto(String name,
                      BigDecimal weight,
                      BigDecimal volume,
                      BigDecimal purchasePrice,
                      String description,
                      Boolean archive) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.archive = archive;
    }

    public ProductDto(Long id,
                      String name,
                      BigDecimal weight,
                      BigDecimal volume,
                      BigDecimal purchasePrice,
                      String description,
                      Boolean archive) {
        this(name, weight, volume, purchasePrice, description, archive);
        this.id = id;
    }
}
