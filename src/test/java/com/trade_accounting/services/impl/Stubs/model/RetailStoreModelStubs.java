package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Payout;
import com.trade_accounting.models.RetailStore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;

public class RetailStoreModelStubs {
    public static RetailStore getRetailStore(Long id) {
        return RetailStore.builder()
                .id(id)
                .name("name")
                .isActive(true)
                .activityStatus("active")
                .revenue(BigDecimal.valueOf(1234))
                .organization(getCompany(id))
                .salesInvoicePrefix("prefix")
                .defaultTaxationSystem("sistem")
                .orderTaxationSystem("order")
                .cashiers(List.of(getEmployee(id)))
                .build();
    }
}
