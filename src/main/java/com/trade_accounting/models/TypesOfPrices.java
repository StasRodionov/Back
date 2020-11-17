package com.trade_accounting.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class TypesOfPrices {
    private long id;
    private String name;
    private long sortNumber;
}
