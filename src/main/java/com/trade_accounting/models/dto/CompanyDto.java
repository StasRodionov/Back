package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private Long id;

    private String name;

    private String inn;

    private String sortNumber;

    private String phone;

    private String fax;

    private String email;

    private String payerVat;

    private String address;

    private String commentToAddress;

    private String leader;

    private String leaderManagerPosition;

    private String leaderSignature;

    private String chiefAccountant;

    private String chiefAccountantSignature;

    private String stamp;

    private LegalDetailDto legalDetailDto;
}
