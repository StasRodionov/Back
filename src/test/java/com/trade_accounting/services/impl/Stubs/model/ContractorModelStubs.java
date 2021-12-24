package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Contractor;

public class ContractorModelStubs {
    public static Contractor getContractor(Long id) {
        return Contractor.builder()
                .id(id)
                .build();
    }
}
