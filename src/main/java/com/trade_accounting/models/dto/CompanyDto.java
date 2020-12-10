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

    private Boolean payerVat;

    private String address;

    private String commentToAddress;

    private String leader;

    private String leaderManagerPosition;

    private String leaderSignature;

    private String chiefAccountant;

    private String chiefAccountantSignature;

    private String stamp;

    private LegalDetailDto legalDetailDto;

    public CompanyDto(Long id,
                      String name,
                      String inn,
                      String sortNumber,
                      String phone,
                      String fax,
                      String email,
                      Boolean payerVat,
                      String address,
                      String commentToAddress,
                      String leader,
                      String leaderManagerPosition,
                      String leaderSignature,
                      String chiefAccountant,
                      String chiefAccountantSignature,
                      String stamp,
                      Long legalDetailId) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.sortNumber = sortNumber;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.payerVat = payerVat;
        this.address = address;
        this.commentToAddress = commentToAddress;
        this.leader = leader;
        this.leaderManagerPosition = leaderManagerPosition;
        this.leaderSignature = leaderSignature;
        this.chiefAccountant = chiefAccountant;
        this.chiefAccountantSignature = chiefAccountantSignature;
        this.stamp = stamp;
        this.legalDetailDto = new LegalDetailDto();
        this.legalDetailDto.setId(legalDetailId);
    }

    public CompanyDto(String name, String inn, String sortNumber, String phone, String fax, String email, Boolean payerVat, String address, String commentToAddress, String leader, String leaderManagerPosition, String leaderSignature, String chiefAccountant, String chiefAccountantSignature, String stamp, Long legalDetailId) {
        this.name = name;
        this.inn = inn;
        this.sortNumber = sortNumber;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.payerVat = payerVat;
        this.address = address;
        this.commentToAddress = commentToAddress;
        this.leader = leader;
        this.leaderManagerPosition = leaderManagerPosition;
        this.leaderSignature = leaderSignature;
        this.chiefAccountant = chiefAccountant;
        this.chiefAccountantSignature = chiefAccountantSignature;
        this.stamp = stamp;
        this.legalDetailDto.setId(legalDetailId);
    }
}
