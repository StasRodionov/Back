package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Payout;
import com.trade_accounting.models.Prepayout;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;

public class PrepayoutModelStubs {
    public static Prepayout getPrepayout(Long id) {
        return Prepayout.builder()
                .id(1L)
                .date(LocalDateTime.parse(LocalDateTime.now().toString()))
                .retailStore(getRetailStore(1L))
                .contractor(getContractor(1L))
                .company(getCompany(1L))
                .cash(new BigDecimal(3434))
                .cashless(new BigDecimal(433))
                .discount(new BigDecimal(873))
                .sum(new BigDecimal(343))
                .isSent(false)
                .isPrint(false)
                .comment("comment")
                .build();
    }
}
