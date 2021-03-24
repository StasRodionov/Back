package com.trade_accounting.models.dto;

import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfPrice;
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

    private TypeOfContractorDto typeOfContractorDto;
    private String typeOfContractorName;

    private TypeOfPriceDto typeOfPriceDto;
    private String typeOfPriceName;

    private List<BankAccountDto> bankAccountDto;
    private Integer bankAccountSize;

    private LegalDetailDto legalDetailDto;
    private String legalDetailInn;

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
                         //ContractorGroupDto contractorGroupDto,
                         String contractorGroupName,
                         //TypeOfContractorDto typeOfContractorDto,
                         String typeOfContractorName,
                         //TypeOfPriceDto typeOfPriceDto,
                         String typeOfPriceName,
                        // List<BankAccountDto> bankAccountDto,
                         Integer bankAccountSize,
                         //LegalDetailDto legalDetailDto,
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
        this.contractorGroupDto = contractorGroupDto;
        this.contractorGroupName = contractorGroupName;
        this.typeOfContractorDto = typeOfContractorDto;
        this.typeOfContractorName = typeOfContractorName;
        this.typeOfPriceDto = typeOfPriceDto;
        this.typeOfPriceName = typeOfPriceName;
        this.bankAccountDto = bankAccountDto;
        this.bankAccountSize = bankAccountSize;
        this.legalDetailDto = legalDetailDto;
        this.legalDetailInn = legalDetailInn;
    }

    public ContractorDto(Long id,
                         String name, @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber, String phone, String fax, String email,
                         String address, String commentToAddress, String comment,
                         ContractorGroupDto contractorGroupDto,
                         //String contractorGroupName,
                         TypeOfContractorDto typeOfContractorDto,
                         //String typeOfContractorName,
                         TypeOfPriceDto typeOfPriceDto,
                         //String typeOfPriceName,
                         //List<BankAccountDto> bankAccountDto,
                         Integer bankAccountSize,
                         LegalDetailDto legalDetailDto
                         //String legalDetailInn
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
        this.contractorGroupName = contractorGroupName;
        this.typeOfContractorDto = typeOfContractorDto;
        this.typeOfContractorName = typeOfContractorName;
        this.typeOfPriceDto = typeOfPriceDto;
        this.typeOfPriceName = typeOfPriceName;
        this.bankAccountDto = bankAccountDto;
        this.bankAccountSize = bankAccountSize;
        this.legalDetailDto = legalDetailDto;
        this.legalDetailInn = legalDetailInn;
    }

    // com.trade_accounting.services.impl.ContractorServiceTest
//    public ContractorDto(Long id, String name,
//                         @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
//                         String sortNumber, String phone, String fax, String email,
//                         String address, String commentToAddress, String comment,
//                         ContractorGroup contractorGroup,
//                         TypeOfContractor typeOfContractor,
//                         TypeOfPrice typeOfPrice,
//                         long bankAccountSize,
//                         LegalDetail legalDetail
//
//    ) {
//        this.id = id;
//        this.name = name;
//        this.inn = inn;
//        this.sortNumber = sortNumber;
//        this.phone = phone;
//        this.fax = fax;
//        this.email = email;
//        this.address = address;
//        this.commentToAddress = commentToAddress;
//        this.comment = comment;
//        this.contractorGroupDto = (ContractorGroupDto)contractorGroup ;
////        this.contractorGroupName = contractorGroupName;
////        this.typeOfContractorDto = typeOfContractorDto;
////        this.typeOfContractorName = typeOfContractorName;
////        this.typeOfPriceDto = typeOfPriceDto;
////        this.typeOfPriceName = typeOfPriceName;
////        this.bankAccountDto = bankAccountDto;
//        //this.bankAccountSize = bankAccountSize;
////        this.legalDetailDto = legalDetailDto;
////        this.legalDetailInn = legalDetailInn;
//    }

}
