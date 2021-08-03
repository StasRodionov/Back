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
@Table(name = "purchase_forecast")
public class PurchaseForecast {
    @Id
    @NotNull
    @Column(name = "id")
    private Long id;


    @Column(name = "days")
    private Long reservedDays;

    @Column(name = "products")
    private Long reservedProducts;

    private Boolean ordered;
}