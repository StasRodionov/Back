package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.IssuedInvoice;
import com.trade_accounting.models.TypeOfInvoice;

import java.time.LocalDateTime;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;

public class IssuedInvoiceModelStubs {
    public static IssuedInvoice getIssuedInvoice(Long id) {
        return IssuedInvoice.builder()
                .id(id)
                .date(LocalDateTime.now())
                .company(getCompany(1L))
                .contractor(getContractor(1L))
                .isSpend(true)
                .comment("Комментарий")
                .build();
    }
}
