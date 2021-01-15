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
    private TypeOfInvoice typeOfInvoice;
    @NotNull
    private Company company;
    @NotNull
    private Contractor contractor;

    private boolean isSpend;

    public InvoiceDto(LocalDateTime date, TypeOfInvoice typeOfInvoice, Company company, Contractor contractor) {
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.company = company;
        this.contractor = contractor;
    }

}
