package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "retail_stores")
public class RetailStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "is_active")
    @ColumnDefault("false")
    private Boolean isActive;

    @Column(name = "activity_status")
    private String activityStatus;

    @Column(name = "revenue")
    private BigDecimal revenue;

    @OneToOne(fetch = FetchType.LAZY)
    private Company organization;

    @Column(name = "sales_invoice_prefix")
    private String salesInvoicePrefix;

    @Column(name = "default_taxation_system")
    private String defaultTaxationSystem;

    @Column(name = "order_taxation_system")
    private String orderTaxationSystem;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Employee> cashiers;

    public RetailStore(String name, Boolean isActive, String activityStatus, BigDecimal revenue,
                       Company organization, String salesInvoicePrefix, String defaultTaxationSystem,
                       String orderTaxationSystem, List<Employee> cashiers) {
        this.name = name;
        this.isActive = isActive;
        this.activityStatus = activityStatus;
        this.revenue = revenue;
        this.organization = organization;
        this.salesInvoicePrefix = salesInvoicePrefix;
        this.defaultTaxationSystem = defaultTaxationSystem;
        this.orderTaxationSystem = orderTaxationSystem;
        this.cashiers = cashiers;
    }

}
