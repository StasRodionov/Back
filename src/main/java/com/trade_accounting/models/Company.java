package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "inn", unique = true)
    private String inn;

    @Column(name = "sort_number")
    private String sortNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "payer_vat")
    private Boolean payerVat;

    @Column(name = "address")
    private String address;

    @Column(name = "comment_to_address")
    private String commentToAddress;

    @Column(name = "leader")
    private String leader;

    @Column(name = "leader_manager_position")
    private String leaderManagerPosition;

    @Column(name = "leader_signature")
    private String leaderSignature;

    @Column(name = "chief_accountant")
    private String chiefAccountant;

    @Column(name = "chief_accountant_signature")
    private String chiefAccountantSignature;

    @Column(name = "stamp")
    private String stamp;

    @OneToOne(fetch = FetchType.LAZY)
    private LegalDetail legalDetail;

    public Company(String name, String inn, String sortNumber, String phone, String fax, String email, Boolean payerVat, String address, String commentToAddress, String leader, String leaderManagerPosition, String leaderSignature, String chiefAccountant, String chiefAccountantSignature, String stamp, LegalDetail legalDetail) {
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
        this.legalDetail = legalDetail;
    }
}
