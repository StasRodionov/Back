package com.trade_accounting.config.init;

import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.models.dto.InternalOrderProductsDto;
import com.trade_accounting.services.interfaces.InternalOrderProductService;
import com.trade_accounting.services.interfaces.InternalOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InternalOrderInit {
    private final InternalOrderService internalOrderService;
    private final InternalOrderProductService internalOrderProductService;

    @PostConstruct
    public void init() {
        initInternalOrderProduct();
        initInternalOrder();
    }

    public void initInternalOrderProduct() {
        for (long i = 1L; i <= 15; i++) {
            internalOrderProductService.create(
                    InternalOrderProductsDto.builder()
                            .amount(BigDecimal.valueOf((Math.random() * ((100 - 10) + 1)) + 10))
                            .price(BigDecimal.valueOf((Math.random() * ((100 - 10) + 1)) + 10))
                            .productId(i)
                            .build()
            );
        }
    }

    public void initInternalOrder() {
        for (long i = 1L; i <= 13; i += 3) {
            internalOrderService.create(InternalOrderDto.builder()
                    .internalOrderProductsIds(List.of(i, i + 1L, i + 2L))
                    .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .companyId(i)
                    .isSent(false)
                    .isPrint(false)
                    .comment("Внутренний заказ " + i)
                    .build()
            );
        }
    }
}
