package com.trade_accounting.config.init;

import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.services.interfaces.InternalOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InternalOrderInitializer {
    private final InternalOrderService internalOrderService;

    @PostConstruct
    public void initInternalOrder() {
        for (long i = 1L; i <= 15; i += 3) {
            internalOrderService.create(InternalOrderDto.builder()
                    .id(null)
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
