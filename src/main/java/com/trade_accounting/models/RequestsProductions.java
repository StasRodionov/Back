package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requsts_productions")
public class RequestsProductions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  //осталось добавить дату
    @OneToOne
    private TechnicalCard technicalCard;

    private Integer volume;

    @OneToOne
    private Warehouse warehouse;

}
