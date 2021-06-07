package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {

    private Long id;

    private String number;

    //private String contractDate; //добавил поле во фронте есть

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

    public ContractDto(Long id,
                       String number,
                       LocalDate contractDate,
                       Long companyId,
                       Long bankAccountId,
                       Long contractorId,
                       BigDecimal amount,
                       Boolean archive,
                       String comment,
                       Long legalDetailId) {
        this.id = id;
        this.number = number;
        // this.contractDate = contractDate; //добавил
        this.contractDate = contractDate;
        this.companyDto = new CompanyDto();
        this.companyDto.setId(companyId);
        this.bankAccountDto = new BankAccountDto();
        this.bankAccountDto.setId(bankAccountId);
        this.contractorDto = new ContractorDto();
        this.contractorDto.setId(contractorId);
        this.amount = amount;
        this.archive = archive;
        this.comment = comment;
        this.legalDetailDto = new LegalDetailDto();
        this.legalDetailDto.setId(legalDetailId);
    }
}
