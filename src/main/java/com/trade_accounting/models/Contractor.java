package com.trade_accounting.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Contractor {

    private Long id;
    private String name;
    private String sortNumber;
    private String phone;
    private String fax;
    private String email;
    private String address;
    private String commentToAddress;
    private String comment;
}
