package com.trade_accounting.models.dto;


import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.TypeOfPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Iterator;
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

    private List<TypeOfPriceDto> typeOfPriceDto;

    private TaxSystemDto taxSystemDto;

    private List<ImageDto> imageDto;

    private ProductGroupDto productGroupDto;

    private AttributeOfCalculationObjectDto attributeOfCalculationObjectDto;



    public ProductDto(Long id,
                      String name,
                      BigDecimal weight,
                      BigDecimal volume,
                      BigDecimal purchasePrice,
                      String description,
                      Boolean archive,
                      Long unitId,
                      Long contractorId,
                      Long taxSystemId,
                      Long productGroupId) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.archive = archive;
        this.unitDto.setId(unitId);
        this.contractorDto.setId(contractorId);
        this.taxSystemDto.setId(taxSystemId);
        this.productGroupDto.setId(productGroupId);
    }
//
//    public ProductDto(Long id, String name, BigDecimal weight, BigDecimal volume, BigDecimal purchasePrice, String description, Boolean archive) {
//        this.id = id;
//        this.name = name;
//        this.weight = weight;
//        this.volume = volume;
//        this.purchasePrice = purchasePrice;
//        this.description = description;
//        this.archive = archive;
//    }
//
//    public ProductDto(String name, BigDecimal weight, BigDecimal volume, BigDecimal purchasePrice, String description, Boolean archive) {
//        this.name = name;
//        this.weight = weight;
//        this.volume = volume;
//        this.purchasePrice = purchasePrice;
//        this.description = description;
//        this.archive = archive;
//    }
}
