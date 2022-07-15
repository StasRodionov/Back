package com.trade_accounting.Stubs.model.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.Stubs.ModelStubs;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Andrey Melnikov and Pavel Andrusov
 * @since 05.08.2021
 */
public class PriceListModelStubs {
    public static PriceList getPriceList(Long id) {
        return PriceList.builder()
                .id(id)
                .number("NumberOne")
                .date(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .company(ModelStubs.getCompany(id))
                .isSent(false)
                .isPrint(false)
                .comment("comment" + id)
                .typeOfPrice(ModelStubs.getTypeOfPrice(1L))
                .build();
    }
}
