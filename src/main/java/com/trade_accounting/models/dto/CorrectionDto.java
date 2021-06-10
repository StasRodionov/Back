package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorrectionDto {

    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long companyId;

    private Boolean correctionIsSent = false;

    private Boolean correctionIsPrint = false;

    private Boolean writeOffProduct = false;

    private String comment;

    private List<Long> correctionProductIds;
}
