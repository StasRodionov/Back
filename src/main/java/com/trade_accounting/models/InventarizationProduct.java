package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventarization_products")
public class InventarizationProduct {

    private Long id;
    private Product product;
    //модельку остатков
    private BigDecimal actualProduct;
    private BigDecimal price;
}
