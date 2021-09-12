package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnFromBuyersDto {
    private Long id;

    @DateTimeFormat(pattern = "dd.MM.yyyy k:m")
    private LocalDateTime date;

    private Long warehouseId;

    private Long contractorId;

    private Long companyId;

    private BigDecimal totalPrice;

    private Long contractId;

    private String comment;

    private Boolean isConducted;
}
