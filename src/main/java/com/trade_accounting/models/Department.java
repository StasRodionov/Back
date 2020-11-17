package com.trade_accounting.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Department {

    private Long id;
    private String name;
    private String sortNumber;
}
