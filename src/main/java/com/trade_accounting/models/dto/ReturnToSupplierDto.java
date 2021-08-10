package com.trade_accounting.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnToSupplierDto {

    private Long id;

    private String date;

    private Long warehouseId;

    private Long companyId;

    private Long contractorId;

    private Long contractId;

    private Boolean isSend;

    private Boolean isPrint;

    private String comment;

}
