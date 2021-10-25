package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueDto {

    private Long id;

    private Long productId;

    private String description;

    private Long unitId;

    private Integer itemNumber;

    private Long amountAcceptance;

    private String incomingNumberDate;

    private BigDecimal amountShipment;

}