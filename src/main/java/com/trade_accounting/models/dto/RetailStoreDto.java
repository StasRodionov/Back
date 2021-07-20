package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetailStoreDto {

    private Long id;

    @NotNull
    private String name;

    private Boolean isActive;

    private String activityStatus;

    private BigDecimal revenue;

    @NotNull
    private Long companyId;

    private String salesInvoicePrefix;

    private String defaultTaxationSystem;

    private String orderTaxationSystem;

    private List<Long> cashiersIds;

//    public RetailStoreDto(String name, Boolean isActive, String activityStatus,
//                          BigDecimal revenue, CompanyDto organization,
//                          String salesInvoicePrefix, String defaultTaxationSystem,
//                          String orderTaxationSystem, List<EmployeeDto> cashiers) {
//        this.name = name;
//        this.isActive = isActive;
//        this.activityStatus = activityStatus;
//        this.revenue = revenue;
//        this.organizationDto = organization;
//        this.salesInvoicePrefix = salesInvoicePrefix;
//        this.defaultTaxationSystem = defaultTaxationSystem;
//        this.orderTaxationSystem = orderTaxationSystem;
//        this.cashiersDto = cashiers;
//    }
//
//    public RetailStoreDto(Long id, String name, Boolean isActive, String activityStatus,
//                          BigDecimal revenue, Long organizationId,
//                          String salesInvoicePrefix, String defaultTaxationSystem,
//                          String orderTaxationSystem) {
//        this.id = id;
//        this.name = name;
//        this.isActive = isActive;
//        this.activityStatus = activityStatus;
//        this.revenue = revenue;
//        this.organizationDto = new CompanyDto();
//        this.organizationDto.setId(organizationId);
//        this.salesInvoicePrefix = salesInvoicePrefix;
//        this.defaultTaxationSystem = defaultTaxationSystem;
//        this.orderTaxationSystem = orderTaxationSystem;
//    }

}
