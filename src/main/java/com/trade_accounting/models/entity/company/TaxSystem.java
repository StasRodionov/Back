package com.trade_accounting.models.entity.company;

import com.trade_accounting.models.entity.warehouse.ProductGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tax_systems")
@Builder
public class TaxSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sort_number")
    private String sortNumber;

    @OneToMany(mappedBy = "taxSystem")
    private List<ProductGroup> productGroup;

    public TaxSystem(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }

}
