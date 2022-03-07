package com.trade_accounting.models.dto.indicators;

import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.indicators.TypeOfAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuditDto {

	private Long id;
	
	private String description;
	
	private String date;

	private Long employeeId;

	private TypeOfAudit typeOfAudit;
}
