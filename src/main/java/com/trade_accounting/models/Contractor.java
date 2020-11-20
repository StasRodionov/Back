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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contractors")
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$")
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

    @Column(name = "address")
    private String address;

    @Column(name = "comment_to_address")
    private String commentToAddress;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private ContractorGroup contractorGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfContractor typeOfContractor;

    @OneToOne(fetch = FetchType.LAZY)
    private TypeOfPrice typeOfPrice;

    @OneToMany(fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;


}
