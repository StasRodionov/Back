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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


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

    @OneToMany(fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfContractor typeOfContractor;

    @Column(name = "legal_detail_surname")
    private String legalDetailSurname;

    @Column(name = "legal_detail_name")
    private String legalDetailName;

    @Column(name = "legal_detail_patronymic")
    private String legalDetailPatronymic;

    @Column(name = "legal_detail_reg_address")
    private String legalDetailRegAddress;

    @Column(name = "legal_detail_comment_to_address")
    private String legalDetailCommentToAddress;

    @Column(name = "legal_detail_inn")
    private String legalDetailInn;

    @Column(name = "legal_detail_okpo")
    private String legalDetailOkpo;

    @Column(name = "legal_detail_ogrnip")
    private String legalDetailOgrnip;

    @Column(name = "legal_detail_cert_num")
    private String legalDetailCertNum;

    @Column(name = "legal_detail_cert_date")
    private String legalDetailCertDate;
}
