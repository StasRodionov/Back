package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierAccountDto {

    private Long id;

    private String date;

    private Long companyId;

    private Long warehouseId;

    private Long contractId;

    private Long contractorId;

    private Boolean isSpend;

    private String comment;

}
