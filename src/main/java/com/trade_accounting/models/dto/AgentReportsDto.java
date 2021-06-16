package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentReportsDto {

    private Long id;

    private String documentType;

    private LocalDateTime time;

    private CompanyDto companyDto;

    private ContractorDto contractorDto;

    private Long sum;

    private Long remunirationSum;

    private Long comitentSum;

    private Long paid;

    private String status;

    private Long sent;

    private Long printed;

    private String commentary;

}
