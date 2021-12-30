package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.InvoicesStatus;

public class InvoicesStatusStubs {
    public static InvoicesStatus getInvoicesStatus(Long id) {
        return InvoicesStatus.builder()
                .id(id)
                .statusName("Новый" + id)
                .build();
    }
}
