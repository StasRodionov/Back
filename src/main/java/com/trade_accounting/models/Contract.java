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
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "contract_date", columnDefinition = "date default current_date")
    private LocalDate contractDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    //@Column(name = "payment_account")
    //private PaymentAccount paymentAccount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @Column(name = "sum", columnDefinition = "Money default 0.0")
    private BigDecimal sum;

    @Column(name = "archive", columnDefinition = "boolean default false")
    private boolean archive;

    @Column(name = "comment")
    private String comment;
}
