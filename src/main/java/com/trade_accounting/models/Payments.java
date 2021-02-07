package com.trade_accounting.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_of_payment")
    private TypeOfPayment typeOfPayment;

    @Column(name = "number")
    @NotNull
    @ColumnDefault(value = "00001")
    private String number;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "company")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Column(name = "contractor")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @Column(name = "contract")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    //Переделать в enum
    @Column(name = "cost_item")
    @NotNull
    private String costItem;

    @Column(name = "project")
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @Column(name = "sum")
    private BigDecimal sum;

}
