package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.RetailReturn;
import com.trade_accounting.models.RetailStore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;

public class RetailReturnModelStubs {

    public static RetailReturn getRetailReturn(Long id) {
        return RetailReturn.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .retailStore(getRetailStore(1L))
                .cashAmount(new BigDecimal(1000))
                .cashlessAmount(new BigDecimal(1000))
                .isSpend(false)
                .isSend(false)
                .isPrint(false)
                .comment("Комент 1")
                .build();
    }
}
