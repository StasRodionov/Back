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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight", scale = 3)
    private BigDecimal weight;

    @Column(name = "volume", scale = 6)
    private BigDecimal volume;

    @Column(name = "purchase_price", scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Unit unit;

    @Column(name = "archive")
    private Boolean archive = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @OneToMany(fetch = FetchType.LAZY)
    private List<TypeOfPrice> typeOfPrices;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaxSystem taxSystem;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Image> images;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductGroup productGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private AttributeOfCalculationObject attributeOfCalculationObject;

    public Product(String name,
                   BigDecimal purchasePrice,
                   String description,
                   BigDecimal weight,
                   BigDecimal volume,
                   Boolean archive,
                   Unit unit,
                   ProductGroup productGroup,
                   TaxSystem taxSystem,
                   Contractor contractor,
                   AttributeOfCalculationObject attributeOfCalculationObject) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.unit = unit;
        this.archive = archive;
        this.contractor = contractor;
        this.taxSystem = taxSystem;
        this.productGroup = productGroup;
        this.attributeOfCalculationObject = attributeOfCalculationObject;

    }

    public Product(Long id, String name, BigDecimal purchasePrice, String description, BigDecimal weight, BigDecimal volume, Boolean archive, Unit unit, ProductGroup productGroup, TaxSystem taxSystem, Contractor contractor, AttributeOfCalculationObject attributeOfCalculationObject) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.unit = unit;
        this.archive = archive;
        this.contractor = contractor;
        this.taxSystem = taxSystem;
        this.productGroup = productGroup;
        this.attributeOfCalculationObject = attributeOfCalculationObject;
    }
}
