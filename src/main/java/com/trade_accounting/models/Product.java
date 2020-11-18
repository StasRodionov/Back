package com.trade_accounting.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Weight", scale = 3)
    private BigDecimal weight;
    @Column(name = "Volume", scale = 9)
    private BigDecimal volume;
    @Column(name = "PurchasePrice", scale = 2)
    private BigDecimal purchasePrice;
    @Column(name = "Description")
    private String description;

}
