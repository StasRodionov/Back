package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
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
    private String discountCardNumber;
    private List<ContactDto> contactDto;
    private ContractorGroupDto contractorGroupDto;
    private TypeOfPriceDto typeOfPriceDto;
    private List<BankAccountDto> bankAccountDto;
    private LegalDetailDto legalDetailDto;
    private ContractorStatusDto contractorStatusDto;
    private AccessParametersDto accessParametersDto;

    public ContractorDto(Long id,
                         String name,
                         String sortNumber,
                         String phone,
                         String fax,
                         String email,
                         Long addressId,
                         String commentToAddress,
                         String comment,
                         String discountCardNumber,
                         Long contractorGroupId,
                         Long typeOfPriceId,
                         Long legalDetailId,
                         Long contractorStatusId,
                         Long accessParametersId) {
        this.id = id;
        this.name = name;
        this.sortNumber = sortNumber;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.addressDto = new AddressDto();
        this.addressDto.setId(addressId);
        this.commentToAddress = commentToAddress;
        this.comment = comment;
        this.discountCardNumber = discountCardNumber;
        this.contractorGroupDto = new ContractorGroupDto();
        this.contractorGroupDto.setId(contractorGroupId);
        this.typeOfPriceDto = new TypeOfPriceDto();
        this.typeOfPriceDto.setId(typeOfPriceId);
        this.legalDetailDto = new LegalDetailDto();
        this.legalDetailDto.setId(legalDetailId);
        this.contractorStatusDto = new ContractorStatusDto();
        this.contractorStatusDto.setId(contractorStatusId);
        this.accessParametersDto = new AccessParametersDto();
        this.accessParametersDto.setId(accessParametersId);
    }
}
