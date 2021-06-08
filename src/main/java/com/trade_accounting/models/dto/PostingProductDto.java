package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostingProductDto {
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private String warehouseName;

    @NotNull
    private String companyName;

    private boolean postingIsSent;

    private boolean postingIsPrint;

    private String comment;
}
