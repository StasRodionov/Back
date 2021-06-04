package com.trade_accounting.models.dto;

import com.trade_accounting.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

  //  private String address;

    private Address address;

    private String commentToAddress;

    private String leader;

    private String leaderManagerPosition;

    private String leaderSignature;

    private String chiefAccountant;

    private String chiefAccountantSignature;

    private String stamp;

    private LegalDetailDto legalDetailDto;

    private List<BankAccountDto> bankAccountDto;

    public CompanyDto(Long id,
                      String name,
                      String inn,
                      String sortNumber,
                      String phone,
                      String fax,
                      String email,
                      Boolean payerVat,
                    //  String address,
                      Address address,
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


}
