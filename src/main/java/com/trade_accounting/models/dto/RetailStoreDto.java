package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetailStoreDto {

    private Long id;

    @NotNull
    private String name;

    private boolean isActive;
    private String activityStatus;
    private BigDecimal revenue;

    public RetailStoreDto(String name, boolean isActive, String activityStatus, BigDecimal revenue) {
        this.name = name;
        this.isActive = isActive;
        this.activityStatus = activityStatus;
        this.revenue = revenue;
    }

}
