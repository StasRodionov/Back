/*
package com.trade_accounting.config.init;

import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.services.interfaces.RetailStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RetailStoreInit extends InitData{

    private final RetailStoreService retailStoreService;

    @Override
    public void init() {
        initRetailStores();
    }

    public void initRetailStores() {
        retailStoreService.create(
                new RetailStoreDto(
                        null, "Магазин 1", true,
                        "Онлайн", BigDecimal.valueOf(12222),
                        1L, "SI", "ОСН",
                        "ОСН", List.of(1L, 2L, 3L)
                )
        );
        retailStoreService.create(
                new RetailStoreDto(
                        null, "Магазин 2", true,
                        "Был в сети вчера", BigDecimal.valueOf(22222),
                        2L, "SI", "ОСН2",
                        "УСН. Доход", List.of(1L, 2L, 3L)
                )
        );
        retailStoreService.create(
                new RetailStoreDto(
                        null, "Магазин 3", true,
                        "Был в сети 2 часа назад", BigDecimal.valueOf(17777),
                        3L, "SI", "ОСН2",
                        "ЕСХН", List.of(1L, 2L, 3L)
                )
        );
    }
}
*/
