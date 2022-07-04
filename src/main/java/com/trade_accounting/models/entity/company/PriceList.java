package com.trade_accounting.models.entity.company;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @refactor by Andrey Melnikov - 06.08.2021
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price_lists")
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ColumnDefault(value = "00001")
    private String number;

    private LocalDateTime time;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ColumnDefault("false")
    private Boolean sent;

    @ColumnDefault("false")
    private Boolean printed;

    private String commentary;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priceList",
            cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private List<PriceListProduct> products;

    private Boolean isSpend;

    private Boolean isRecyclebin;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfPrice typeOfPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priceList",
            cascade = CascadeType.REMOVE)
    private List<PriceListProductPercents> percents;
}
