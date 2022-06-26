package com.trade_accounting.models.dto.util;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DiscountDto implements Serializable {
    private final Long id;
    private final Boolean isActive;
    @NotNull
    private final String name;
    @NotNull
    private final String type;
    private final Boolean isBonusProgram;
    private final Long bonusProgramId;
}
