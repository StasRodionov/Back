package com.trade_accounting.config.init;

import com.trade_accounting.models.InternalOrderProduct;
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
public class InternalOrderProductInitializer {
    private final InternalOrderProductService internalOrderProductService;

    @PostConstruct
    public void initInternalOrderProduct() {
        for (long i = 1L; i <= 15; i++) {
            internalOrderProductService.create(
                    InternalOrderProductsDto.builder()
                            .id(null)
                            .amount(BigDecimal.valueOf(randomInt(10, 100)))
                            .price(BigDecimal.valueOf(randomInt(10, 100)))
                            .productId(i)
                            .build()
            );
        }
    }

    private int randomInt(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
