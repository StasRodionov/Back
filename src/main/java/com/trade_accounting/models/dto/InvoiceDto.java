package com.trade_accounting.models.dto;

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
    private LocalDateTime date;
    @NotNull
    private String typeOfInvoice;
    @NotNull
    private CompanyDto companyDto;
    @NotNull
    private ContractorDto contractorDto;

    private boolean isSpend;

    public InvoiceDto(Long id, LocalDateTime date, String typeOfInvoice, Long companyId, Long contractorId, boolean isSpend) {
        this.id = id;
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.companyDto = new CompanyDto();
        this.companyDto.setId(companyId);
        this.contractorDto = new ContractorDto();
        this.contractorDto.setId(contractorId);
        this.isSpend = isSpend;
    }
    public InvoiceDto(LocalDateTime date, String typeOfInvoice, Long companyId, Long contractorId, boolean isSpend) {
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.companyDto = new CompanyDto();
        this.companyDto.setId(companyId);
        this.contractorDto = new ContractorDto();
        this.contractorDto.setId(contractorId);
        this.isSpend = isSpend;
    }

}
