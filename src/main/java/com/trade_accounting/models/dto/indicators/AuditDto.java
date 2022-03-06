package com.trade_accounting.models.dto.indicators;

import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.Indicators.TypeOfAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuditDto {

	private Long id;
	
	private String description;
	
	private LocalDateTime localDateTime;

	private EmployeeDto employeeDto;

	private TypeOfAudit typeOfAudit;
}
