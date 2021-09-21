package com.trade_accounting.models.dto;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptanceDto {

    private Long id;

    private String incomingNumber;

    private String comment;

    private String incomingNumberDate;

    private String whenСhangedDate;

    private Long contractorId;

    private Long warehouseId;

    private Long contractId;

    private Long companyId;

    private Long employeeChangedId;

    private Long projectId;

    Boolean isSent;

    Boolean isPrint;

    Boolean isSpend;

    private List<AcceptanceProductionDto> acceptanceProduction;
}
