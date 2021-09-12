package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "return_from_buyers")
public class ReturnFromBuyers {
    @Id
    private Long id;

    @DateTimeFormat(pattern = "dd.MM.yyyy k:m")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    private String comment;

    @ColumnDefault("false")
    private Boolean isConducted;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}