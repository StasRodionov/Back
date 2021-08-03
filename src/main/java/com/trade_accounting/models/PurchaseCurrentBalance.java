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
import javax.persistence.Id;
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
@Table(name = "purchase_current_balance")
public class PurchaseCurrentBalance {
    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @Column(name = "rest")
    private Long restOfTheWarehouse;

    @Column(name = "reserve")
    private Long productsReserve;

    @Column(name = "await")
    private Long productsAwaitingOrder;

    @Column(name = "available")
    private Long productsAvailableForOrder;

    @Column(name = "days_store")
    private Long daysStoreOnTheWarehouse;
}