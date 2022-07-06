package com.trade_accounting.models.entity.company;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price_lists")
@EqualsAndHashCode
public class PriceList extends OperationsAbstract {

    @NotNull
    @ColumnDefault(value = "00001")
    private String number;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priceList",
            cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private List<PriceListProduct> products;

    private Boolean isSpend;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfPrice typeOfPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priceList",
            cascade = CascadeType.REMOVE)
    private List<PriceListProductPercents> percents;
}
