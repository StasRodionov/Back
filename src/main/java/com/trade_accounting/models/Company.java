package com.trade_accounting.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sortNumber;
    private String phone;
    private String fax;
    private String email;
    private Boolean payerVAT;
    private String address;
    private String commentToAddress;
    private String leader;
    private String leaderManagerPosition;
    private String leaderSignature;
    private String chiefAccountant;
    private String chiefAccountantSignature;
    private String stamp;
}
