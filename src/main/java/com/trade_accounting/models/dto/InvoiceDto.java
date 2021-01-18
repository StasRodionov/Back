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

    public InvoiceDto(LocalDateTime date, String typeOfInvoice, CompanyDto companyDto, ContractorDto contractorDto) {
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.companyDto = companyDto;
        this.contractorDto = contractorDto;
    }

}
