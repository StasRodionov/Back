package com.trade_accounting.models.dto;

import com.trade_accounting.models.PaymentMethods;
import com.trade_accounting.models.TypeOfPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;

    private String typeOfPayment;

    private String paymentMethods;

    @NotNull
    private String number;

    private String time;

    @NotNull
    private Long companyId;

    @NotNull
    private Long contractorId;

    private Long contractId;

    private Long projectId;

    private BigDecimal sum;

//    public PaymentDto(Long id,
//                      TypeOfPayment typeOfPayment,
//                      PaymentMethods paymentMethods,
//                      String number,
////                      LocalDateTime time,
//                      String time,
//                      Long companyId,
//                      Long contractorId,
//                      Long contractId,
//                      Long projectId,
//                      BigDecimal sum) {
//        this.id = id;
//        this.typeOfPayment = typeOfPayment;
//        this.paymentMethods = paymentMethods;
//        this.number = number;
//        this.time = time;
//        this.companyDto = new CompanyDto();
//        this.companyDto.setId(companyId);
//        this.contractorDto = new ContractorDto();
//        this.contractorDto.setId(contractorId);
//        this.contractDto = new ContractDto();
//        this.contractDto.setId(contractId);
//        this.projectDto = new ProjectDto();
//        this.projectDto.setId(projectId);
//        this.sum = sum;
//    }
}
