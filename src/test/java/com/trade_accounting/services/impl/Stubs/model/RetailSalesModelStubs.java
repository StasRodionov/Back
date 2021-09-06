package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.RetailSales;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;

public class RetailSalesModelStubs {

    public static RetailSales getRetailSales(Long id) {
        return  RetailSales.builder()
                .id(1L)
                .time("2021-08-11")
                .retailStore(getRetailStore(1L))
                .contractor(getContractor(1L))
                .company(getCompany(1L))
                .sumCash(BigDecimal.valueOf(120000.00))
                .sumNonСash(BigDecimal.valueOf(0.00))
                .prepayment(BigDecimal.valueOf(0.00))
                .sumDiscount(BigDecimal.valueOf(0.00))
                .sum(BigDecimal.valueOf(120000.00))
                .sent(false)
                .printed(false)
                .comment("Комментарий тест 1")
                .build();
    }
}
