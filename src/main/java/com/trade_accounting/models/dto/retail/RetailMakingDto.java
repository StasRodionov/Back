package com.trade_accounting.models.dto.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailMakingDto {

    Long id;

    @NotNull
    String date;

    @NotNull
    Long retailStoreId;

    @NotNull
    String fromWhom;

    @NotNull
    Long companyId;

    @NotNull
    BigDecimal sum;

    Boolean isPrint;

    Boolean isSent;

    String comment;
}