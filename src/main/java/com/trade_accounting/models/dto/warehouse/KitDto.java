package com.trade_accounting.models.dto.warehouse;


import com.trade_accounting.models.dto.util.FileDto;
import com.trade_accounting.models.dto.util.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KitDto {

    private Long id;

    private String name;

    private String description;

    private Boolean archive = false;

    private BigDecimal weight;

    private BigDecimal volume;

    private BigDecimal purchasePrice;

    private Long unitId;

    private Long contractorId;

    private List<Long> productPriceIds;

    private Long taxSystemId;

    private List<ImageDto> imageDtos;

    private List<FileDto> fileDtos;

    private Long productGroupId;

    private String countryOrigin;

    private int itemNumber;

    private String uniqueCode;

    private String barcode;

    private String externalCode;

    private String saleTax;

    private List<ProductDto> productDto;

    private String indicationCalculation;

    private String marking;

    private Long employeeId;

    private Long departmentId;

    private Boolean accessAll = false;

    private BigDecimal additionalExpenses;

}
