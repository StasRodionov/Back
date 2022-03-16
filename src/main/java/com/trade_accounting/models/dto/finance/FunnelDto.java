package com.trade_accounting.models.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunnelDto {

    private Long id;

    private String statusName;

    private Long count;

    private String time;

    private String conversion;

    private String price;

    //Конструктор для listContractorsDataView
    public FunnelDto(Long id, String statusName, Long count, String conversion) {
        this.id = id;
        this.statusName = statusName;
        this.count = count;
        this.conversion = conversion;
    }
}
