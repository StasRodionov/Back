package com.trade_accounting.models.dto.units;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImportDto {
    private Long id;
    private String task;
    private String start;
    private String end;
    private String status;
}
