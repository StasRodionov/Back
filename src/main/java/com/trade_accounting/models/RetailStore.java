package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
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
    private boolean isActive;

    @Column(name = "activity_status")
    private String activityStatus;

    @Column(name = "revenue")
    private BigDecimal revenue;

    public RetailStore(String name, boolean isActive, String activityStatus, BigDecimal revenue) {
        this.name = name;
        this.isActive = isActive;
        this.activityStatus = activityStatus;
        this.revenue = revenue;
    }

}
