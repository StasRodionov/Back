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
public class CountryDto {
    private Long id;
    private String type;
    private String shortName;
    private String fullName;
    private String digitCode;
    private String twoLetterCode;
    private String threeLetterCode;
}
