package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Company;

public class CompanyModelStubs {
    public static Company getCompany(Long id) {
        return Company.builder()
                .id(id)
                .build();
    }
}
