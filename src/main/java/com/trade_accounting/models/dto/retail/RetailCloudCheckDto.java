package com.trade_accounting.models.dto.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetailCloudCheckDto {

    private Long id;

    private String date;

    @NotNull
    private Long initiatorId;

    @NotNull
    private Long fiscalizationPointId;

    private String status;

    private String cheskStatus;

    private BigDecimal total;

    @NotNull
    private Long currencyId;

    @NotNull
    private Long cashierId;
}
