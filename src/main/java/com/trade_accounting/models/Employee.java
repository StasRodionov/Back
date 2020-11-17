package com.trade_accounting.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    private Long id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String sortNumber;
    private String phone;
    private String inn;
    private String description;
    private String image;
    private String login;
    private String password;
    private String email;
}
