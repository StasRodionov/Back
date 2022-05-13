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
public class ScenarioDto {

    private Long id;

    private String name;

    private String comment;

    private Boolean activeStatus = false;


}
