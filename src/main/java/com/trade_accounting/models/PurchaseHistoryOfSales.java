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
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@ToString
@Table(name = "purchase_history_of_sales")
public class PurchaseHistoryOfSales {
    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @Column(name = "sum")
    private BigDecimal sumOfProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductPrice productPrice;

    @Column(name = "margin")
    private BigDecimal productMargin;//она же прибыль, она же маржа

    @Column(name = "profit")
    private BigDecimal productProfitMargin;//она же profit, она же рентабельность

    @Column(name = "sales_per_day")
    private Long productSalesPerDay;
}