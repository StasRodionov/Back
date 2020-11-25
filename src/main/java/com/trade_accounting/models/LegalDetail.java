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
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "legal_details")
public class LegalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "address")
    private String address;

    @Column(name = "comment_to_address")
    private String commentToAddress;

    @Column(name = "inn", unique = true)
    private String inn;

    @Column(name = "okpo")
    private String okpo;

    @Column(name = "ogrnip")
    private String ogrnip;

    @Column(name = "number_of_the_certificate")
    private String numberOfTheCertificate;

    @Column(name = "date_of_the_certificate")
    private LocalDate dateOfTheCertificate;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfContractor typeOfContractor;
}
