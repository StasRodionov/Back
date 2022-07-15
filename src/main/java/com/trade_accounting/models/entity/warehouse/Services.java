package com.trade_accounting.models.entity.warehouse;


import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.company.TaxSystem;
import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.entity.util.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
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
@Builder
@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "archive")
    private Boolean archive = false;

    @Column(name = "weight", scale = 3)
    private BigDecimal weight;

    @Column(name = "volume", scale = 6)
    private BigDecimal volume;

    @Column(name = "purchase_price", scale = 2)
    private BigDecimal purchasePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductPrice> productPrices;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaxSystem taxSystem;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductGroup productGroup;

    @Column(name = "country")
    private String countryOrigin;

    //Артикул(код товара присваеваемый производителем)
    @Column(name = "item_number")
    private int itemNumber;

    // Уникальный код, который используется при поиске и выводится в печатных формах
    @Column(name = "unique_code")
    private String uniqueCode;

    // Штрихкод
    @Column(name = "barcode")
    private String barcode;

    // Внешний код
    @Column(name = "external_code")
    private String externalCode;

    //НДС
    @Column(name = "sale_tax")
    private String saleTax;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    //Признак предмета расчёта
    // Есть основание полагать, что для этих целей созадана сущность AttributeOfCalculationObject
    @Column(name = "indication_calculation")
    private String indicationCalculation;

    //Маркировка
    @Column(name = "marking")
    private String marking;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @Column(name = "access_all")
    private Boolean accessAll = false;

    // Дополнительные расходы
    @Column(name = "additional_expenses", scale = 2)
    private BigDecimal additionalExpenses;
}
