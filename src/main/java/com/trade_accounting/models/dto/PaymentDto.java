package com.trade_accounting.models.dto;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Project;
import com.trade_accounting.models.TypeOfPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;

    private TypeOfPayment typeOfPayment;

    @NotNull
    private String number;

    private LocalDateTime time;

    @NotNull
    private CompanyDto companyDto;

    @NotNull
    private ContractorDto contractorDto;

    private ContractDto contractDto;

    private ProjectDto projectDto;

    private BigDecimal sum;

    public PaymentDto(Long id,
                      TypeOfPayment typeOfPayment,
                      String number,
                      LocalDateTime time,
                      Long companyId,
                      Long contractorId,
                      Long contractId,
                      Long projectId,
                      BigDecimal sum) {
        this.id = id;
        this.typeOfPayment = typeOfPayment;
        this.number = number;
        this.time = time;
        this.companyDto = new CompanyDto();
        this.companyDto.setId(companyId);
        this.contractorDto = new ContractorDto();
        this.contractorDto.setId(contractorId);
        this.contractDto = new ContractDto();
        this.contractDto.setId(contractId);
        this.projectDto = new ProjectDto();
        this.projectDto.setId(projectId);
        this.sum = sum;
    }
}
