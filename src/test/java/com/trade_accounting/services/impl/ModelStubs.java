package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;

public class ModelStubs {
    public static Company getCompany(Long id) {
        return new Company(
                id, "name",
                "123456789012", "00001",
                "12345678901", "3247239423",
                "email", true, "address",
                "commentToAddress", "leader",
                ""
        );
    }
}
