package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ContractDto {

    private Long id;

    private String number;

    private LocalDate contractDate;

    @NotNull
    private CompanyDto companyDto;

    private BankAccountDto bankAccountDto;

    @NotNull
    private ContractorDto contractorDto;

    private BigDecimal amount;

    private Boolean archive;

    private String comment;

    private LegalDetailDto legalDetailDto;
}
