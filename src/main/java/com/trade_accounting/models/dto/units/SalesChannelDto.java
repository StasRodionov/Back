package com.trade_accounting.models.dto.units;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesChannelDto {

    private Long id;

    private String name;

    private String type;

    private String description;

    public SalesChannelDto(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

}
