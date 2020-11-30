package com.trade_accounting.models.dto;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.LegalDetail;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContractDto {

    private Long id;

    private String number;

    private LocalDate contractDate;

    private Company company;

    private BankAccount bankAccount;

    private Contractor contractor;

    private BigDecimal amount;

    private Boolean archive;

    private String comment;

    private LegalDetail legalDetail;
}
