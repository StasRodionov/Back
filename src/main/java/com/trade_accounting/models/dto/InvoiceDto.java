package com.trade_accounting.models.dto;

import com.trade_accounting.models.TypeOfInvoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

    private Long id;
    @NotNull
    private String date;
    @NotNull
    private String typeOfInvoice;
    @NotNull
    private CompanyDto companyDto;
    @NotNull
    private ContractorDto contractorDto;
    @NotNull
    private WarehouseDto warehouseDto;

    private boolean isSpend;

    public InvoiceDto(Long id,
                      LocalDateTime date,
                      TypeOfInvoice typeOfInvoice,
                      Long companyId,
                      Long contractorId,
                      Long warehouseId,
                      boolean isSpend) {
        this.id = id;
        this.date = date.toString();
        this.typeOfInvoice = typeOfInvoice.toString();
        this.companyDto = new CompanyDto();
        this.companyDto.setId(companyId);
        this.contractorDto = new ContractorDto();
        this.contractorDto.setId(contractorId);
        this.warehouseDto = new WarehouseDto();
        this.warehouseDto.setId(warehouseId);
        this.isSpend = isSpend;
    }
}
