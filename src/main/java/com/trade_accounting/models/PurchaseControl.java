package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@ToString
@Table(name = "purchase_control")
public class PurchaseControl {
    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String productName;

    @NotNull
    @Column(name = "code")
    private Long productCode;

    @NotNull
    @Column(name = "article")
    private Long articleNumber;

    @NotNull
    @Column(name = "measure")
    private String productMeasure;//они же еденицы измерения

    @Column(name = "quantity")
    private Long productQuantity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseHistoryOfSales historyOfSales;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseCurrentBalance currentBalance;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseForecast forecast;
}