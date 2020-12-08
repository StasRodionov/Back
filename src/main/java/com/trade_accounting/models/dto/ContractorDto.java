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

    private TypeOfContractorDto typeOfContractorDto;

    private TypeOfPriceDto typeOfPriceDto;

    private List<BankAccountDto> bankAccountDto;

    private LegalDetailDto legalDetailDto;

    public ContractorDto(Long id,
                         String name,
                         @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$") String inn,
                         String sortNumber,
                         String phone,
                         String fax,
                         String email,
                         String address,
                         String commentToAddress,
                         String comment) {
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
}
