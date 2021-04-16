package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractorDto {
    private Long id;
    private String name;
    private String sortNumber;
    private String phone;
    private String fax;
    private String email;
    private AddressDto addressDto;
    private String commentToAddress;
    private String comment;

    private ContractorGroupDto contractorGroupDto;
    private TypeOfPriceDto typeOfPriceDto;
    private List<BankAccountDto> bankAccountDto;
    private LegalDetailDto legalDetailDto;
}
