package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentReportsDto {

    private Long id;

    private String documentType;

    @NotNull
    private String number;

    private LocalDateTime time;

    @NotNull
    private Long companyId;

    @NotNull
    private Long contractorId;

    @NotNull
    private Long sum;

    @NotNull
    private Long remunirationSum;

    @NotNull
    private Long comitentSum;

    @NotNull
    private Long paid;

    private String status;

    private Long sent;

    private Long printed;

    private String commentary;

}
