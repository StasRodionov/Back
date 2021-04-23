package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalDetailDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private AddressDto addressDto;

    private String commentToAddress;

    private String inn;

    private String okpo;

    private String ogrnip;

    private String numberOfTheCertificate;

    private String dateOfTheCertificate;

    private TypeOfContractorDto typeOfContractorDto;

    public LegalDetailDto(Long id,
                          String lastName,
                          String firstName,
                          String middleName,
                          AddressDto addressDto,
                          String commentToAddress,
                          String inn,
                          String okpo,
                          String ogrnip,
                          String numberOfTheCertificate,
                          LocalDate dateOfTheCertificate,
                          Long typeOfContractorId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.addressDto = addressDto;
        this.commentToAddress = commentToAddress;
        this.inn = inn;
        this.okpo = okpo;
        this.ogrnip = ogrnip;
        this.numberOfTheCertificate = numberOfTheCertificate;
        this.dateOfTheCertificate = dateOfTheCertificate.toString();
        this.typeOfContractorDto = new TypeOfContractorDto();
        this.typeOfContractorDto.setId(typeOfContractorId);
    }
}
