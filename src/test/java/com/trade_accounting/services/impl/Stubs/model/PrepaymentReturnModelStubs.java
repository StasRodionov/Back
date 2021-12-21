package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.PrepaymentReturn;
import java.math.BigDecimal;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;

public class PrepaymentReturnModelStubs {

    public static PrepaymentReturn getPrepaymentReturn(Long id) {
        return PrepaymentReturn.builder().id(1L).time("12:00")
                .retailStore(getRetailStore(2L)).contractor(getContractor(1L))
                .company(getCompany(2L)).sumCash(new BigDecimal(195)).sumNonСash(new BigDecimal(77))
                .sent(true).comment("что-либо").build();
    }
}
