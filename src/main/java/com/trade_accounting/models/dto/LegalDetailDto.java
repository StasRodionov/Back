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

    private Long addressDtoId;

    private String commentToAddress;

    private String inn;

    private String kpp;

    private String okpo;

    private String ogrn;

    private String numberOfTheCertificate;

    private LocalDate dateOfTheCertificate;

    private Long typeOfContractorDtoId;
}
