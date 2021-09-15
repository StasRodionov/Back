package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.MutualSettlements;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getContractor;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getEmployee;

public class MutualSettlementsModelStubs {
    public static MutualSettlements getMutualSettlements(Long id) {
        return MutualSettlements.builder()
                .id(id)
                .contractor(getContractor(1L))
                .employee(getEmployee(1L))
                .initialBalance(111)
                .income(111)
                .expenses(111)
                .finalBalance(111)
                .build();
    }
}
