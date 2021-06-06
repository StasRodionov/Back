package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierAccountsDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private CompanyDto companyDto;

    @NotNull
    private WarehouseDto warehouseDto;

    @NotNull
    private ContractDto contractDto;

    private boolean isSpend;

    private String comment;

    public SupplierAccountsDto(Long id, LocalDateTime date,
                               Long companyId, Long contractId,
                               Long warehouseId, boolean isSpend,
                               String comment) {
        this.id = id;
        this.date = date.toString();
        this.companyDto = new CompanyDto();
        this.companyDto.setId(companyId);
        this.contractDto = new ContractDto();
        this.contractDto.setId(contractId);
        this.warehouseDto = new WarehouseDto();
        this.warehouseDto.setId(warehouseId);
        this.isSpend = isSpend;
        this.comment = comment;
    }
}
