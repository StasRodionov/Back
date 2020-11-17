package com.trade_accounting.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class CounterpartyGroup {
    private long id;
    private String name;
    private String sortNumber;

}
