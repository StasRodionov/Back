package com.trade_accounting.config.init;


import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.services.interfaces.PriceListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author by Stanislav Dusyak and Pavel Andrusov
 * @refactoring by Andrey Melnikov - 06.08.2021
 */

@Component
@RequiredArgsConstructor
public class PriceListInit extends InitData {
    private final PriceListService priceListService;

    @Override
    void init() {
        initPriceList();
    }

    private void initPriceList() {
        for (long i = 1L; i < 6; i++) {
            priceListService.create(PriceListDto.builder()
                    .number("0001" + i)
                    .commentary("Тестовый комментарий" + " " + i)
                    .time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .companyId(i)
                    .sent(false)
                    .printed(false)
                    .build()
            );
        }
    }
}
