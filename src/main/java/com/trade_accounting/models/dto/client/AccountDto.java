package com.trade_accounting.models.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    private Set<Long> employeeIds;

    private Set<Long> contractorIds;

    private Set<Long> companyIds;
}
