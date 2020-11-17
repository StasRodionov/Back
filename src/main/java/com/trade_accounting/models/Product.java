package com.trade_accounting.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Entity
public class Product {
    private long id;
}
