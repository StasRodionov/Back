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
    private String contractorGroupName;
    private long contractorGroupId;

    private TypeOfContractorDto typeOfContractorDto;
    private String typeOfContractorName;
    private long typeOfContractorId;

    private TypeOfPriceDto typeOfPriceDto;
    private String typeOfPriceName;
    private long typeOfPriceId;

    private List<BankAccountDto> bankAccountDto;
    private Integer bankAccountSize;
    private long bankAccountId;

    private LegalDetailDto legalDetailDto;
    private String legalDetailInn;
    private long legalDetailId;

    public ContractorDto(Long id, String name,
                         @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber, String phone, String fax, String email,
                         String address, String commentToAddress, String comment,
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
        this.typeOfPriceDto.setId(typeOfPriceId);

    }

    public ContractorDto(Long id,
                         String name, @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber, String phone, String fax, String email,
                         String address, String commentToAddress, String comment,
                         String contractorGroupName,
                         String typeOfContractorName,
                         String typeOfPriceName,
                         Integer bankAccountSize,
                         String legalDetailInn
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
        this.contractorGroupName = contractorGroupName;
        this.typeOfContractorName = typeOfContractorName;
        this.typeOfPriceName = typeOfPriceName;
        this.bankAccountSize = bankAccountSize;
        this.legalDetailInn = legalDetailInn;
    }

    public ContractorDto(Long id,
                         String name, @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber, String phone, String fax, String email,
                         String address, String commentToAddress, String comment,
                         ContractorGroupDto contractorGroupDto,
                         TypeOfContractorDto typeOfContractorDto,
                         TypeOfPriceDto typeOfPriceDto,
                         Integer bankAccountSize,
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
        this.typeOfContractorDto = typeOfContractorDto;
        this.typeOfPriceDto = typeOfPriceDto;
        this.bankAccountSize = bankAccountSize;
        this.legalDetailDto = legalDetailDto;
    }

    public ContractorDto(Long id, String name,
                         @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber, String phone, String fax, String email,
                         String address, String commentToAddress, String comment
            //,

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

    }


    public ContractorDto(Long id, String name,
                         @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber, String phone, String fax, String email,
                         String address, String commentToAddress, String comment,
                         String contractorGroupName,
                         String typeOfContractorName,
                         String typeOfPriceName,
                         Integer bankAccountSize,
                         String legalDetailInn,
                         long contractorGroupId,
                         long typeOfContractorId,
                         long typeOfPriceId,
                         long legalDetailId
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
        this.contractorGroupName = contractorGroupName;
        this.contractorGroupId = contractorGroupId;
        this.typeOfContractorName = typeOfContractorName;
        this.typeOfContractorId = typeOfContractorId;
        this.typeOfPriceName = typeOfPriceName;
        this.typeOfPriceId = typeOfPriceId;
        this.bankAccountSize = bankAccountSize;
        this.legalDetailInn = legalDetailInn;
        this.legalDetailId = legalDetailId;
    }

}
