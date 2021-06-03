package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostingProductDto {

    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private WarehouseDto warehouseDto;

    @NotNull
    private CompanyDto companyDto;

    private boolean postingIsSent;

    private boolean postingIsPrint;

    private String comment;

    @NotNull
    private List<InvoiceProductDto> invoiceProductDto;
}
