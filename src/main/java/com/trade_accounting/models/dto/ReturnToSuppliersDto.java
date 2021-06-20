package com.trade_accounting.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnToSuppliersDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private WarehouseDto warehouseDto;

    @NotNull
    private CompanyDto companyDto;

    @NotNull
    private ContractorDto contractorDto;

    @NotNull
    private ContractDto contractDto;

    private Boolean isSend;

    private Boolean isPrint;

    private String comment;

    public ReturnToSuppliersDto(Long id, String date, Long warehouseId, Long companyId, Long contractorId, Long contractId, Boolean isSend, Boolean isPrint, String comment) {
        this.id = id;
        this.date = date;
        this.warehouseDto = new WarehouseDto();
        this.warehouseDto.setId(warehouseId);
        this.companyDto = new CompanyDto();
        this.companyDto.setId(companyId);
        this.contractorDto = new ContractorDto();
        this.contractorDto.setId(contractorId);
        this.contractDto = new ContractDto();
        this.contractDto.setId(contractId);
        this.isSend = isSend;
        this.isPrint = isPrint;
        this.comment = comment;
    }
}
