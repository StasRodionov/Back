package com.trade_accounting.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContractDto {

    private Long id;

    private String number;

    private LocalDate contractDate;

    private CompanyDto companyDto;

    private BankAccountDto bankAccountDto;

    private ContractorDto contractorDto;

    private BigDecimal amount;

    private Boolean archive;

    private String comment;

    private LegalDetailDto legalDetailDto;
}
