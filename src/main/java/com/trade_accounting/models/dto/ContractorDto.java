package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
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

    private String discountCardNumber;

    private List<ContactDto> contactDto;

    private ContractorGroupDto contractorGroupDto;

    private TypeOfPriceDto typeOfPriceDto;

    private List<BankAccountDto> bankAccountDto;

    private LegalDetailDto legalDetailDto;

    private ContractorStatusDto contractorStatusDto;

    private AccessParametersDto accessParametersDto;
}
