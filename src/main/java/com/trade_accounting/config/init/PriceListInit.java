package com.trade_accounting.config.init;


import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.PriceListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PriceListInit extends InitData {
    private final PriceListService priceListService;

    @Override
    void init() {
        initPriceList();
    }

    private void initPriceList() {
        for (long i = 1L; i < 5; i ++) {
            priceListService.create(PriceListDto.builder()
                    .number("0001" + i)
                    .commentary("Тестовый комментарий" + " " + i)
                    .time(LocalDateTime.now())
                    .company_id(i)
                    .sent(i)
                    .printed(i)
                    .build()
            );
        }
    }
}
