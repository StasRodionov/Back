package com.trade_accounting.models.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ColumnsMaskDto {

    private Integer gridDtoId;

    private Long employeeDtoId;

    private Integer mask;
}
