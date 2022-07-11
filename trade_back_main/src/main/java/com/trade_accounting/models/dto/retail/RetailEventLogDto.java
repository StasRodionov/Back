package com.trade_accounting.models.dto.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetailEventLogDto {

    private Long id;
    private String docType;
    private Long operationId;
    private String actionType;
    private String sellPoint;
    private String initiator;
    private String details;
    private String api;
}
