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

    @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$")
    private String inn;

    private String sortNumber;

    private String phone;

    private String fax;

    private String email;

    private String address;

    private String commentToAddress;

    private String comment;

    private ContractorGroupDto contractorGroupDto;
    private String contractorGroup;

    private TypeOfContractorDto typeOfContractorDto;
    private String typeOfContractor;

    private TypeOfPriceDto typeOfPriceDto;
    private String typeOfPrice;

    private List<BankAccountDto> bankAccountDto;
    private Integer bankAccount;

    private LegalDetailDto legalDetailDto;
    private String legalDetail;



    public ContractorDto(Long id,
                         String name,
                         @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber,
                         String phone,
                         String fax,
                         String email,
                         String address,
                         String commentToAddress,
                         String comment,
                         Long typeOfPriceId
                         ) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.sortNumber = sortNumber;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.address = address;
        this.commentToAddress = commentToAddress;
        this.comment = comment;
        this.typeOfPriceDto = new TypeOfPriceDto();
        this.typeOfPriceDto.setId(typeOfPriceId);
    }



    public ContractorDto(Long id, String name,
                         @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber, String phone, String fax, String email,
                         String address, String commentToAddress, String comment,
                         String contractorGroup,
                         String typeOfContractor,
                         String typeOfPrice,
                         //String bankAccount,
                         Integer bankAccount,
                         String legalDetail
    ) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.sortNumber = sortNumber;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.address = address;
        this.commentToAddress = commentToAddress;
        this.comment = comment;
        this.contractorGroup = contractorGroup;
        this.typeOfContractor = typeOfContractor;
        this.typeOfPrice = typeOfPrice;
        this.bankAccount = bankAccount;
        this.legalDetail = legalDetail;
    }

    public ContractorDto(Long id, String name,
                         @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber, String phone, String fax,
                         String email, String address, String commentToAddress,
                         String comment,
                         ContractorGroupDto contractorGroupDto,
                         TypeOfContractorDto typeOfContractorDto,
                         TypeOfPriceDto typeOfPriceDto,
                         Integer bankAccount,
                         LegalDetailDto legalDetailDto

    ) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.sortNumber = sortNumber;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.address = address;
        this.commentToAddress = commentToAddress;
        this.comment = comment;
        this.contractorGroupDto = contractorGroupDto;
        this.contractorGroup = contractorGroup;
        this.typeOfContractorDto = typeOfContractorDto;
        this.typeOfContractor = typeOfContractor;
        this.typeOfPriceDto = typeOfPriceDto;
        this.typeOfPrice = typeOfPrice;
        this.bankAccountDto = bankAccountDto;
        this.bankAccount = bankAccount;
        this.legalDetailDto = legalDetailDto;
        this.legalDetail = legalDetail;
    }


}
