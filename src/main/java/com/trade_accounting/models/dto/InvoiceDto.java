package com.trade_accounting.models.dto;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
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
    private LocalDateTime date;
    @NotNull
    private String typeOfInvoice;
    @NotNull
    private CompanyDto company;
    @NotNull
    private ContractorDto contractor;

    private boolean isSpend;

    public InvoiceDto(LocalDateTime date, String typeOfInvoice, CompanyDto company, ContractorDto contractor) {
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.company = company;
        this.contractor = contractor;
    }

}
