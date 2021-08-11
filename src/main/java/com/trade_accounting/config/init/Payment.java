/*
package com.trade_accounting.config.init;

import com.trade_accounting.models.PaymentMethods;
import com.trade_accounting.models.TypeOfPayment;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.ContractService;
import com.trade_accounting.services.interfaces.ContractorService;
import com.trade_accounting.services.interfaces.PaymentService;
import com.trade_accounting.services.interfaces.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Payment extends InitData {
    private final PaymentService paymentService;
    private final CompanyService companyService;
    private final ContractorService contractorService;
    private final ProjectService projectService;
    private final ContractService contractService;

    @Override
    void init() {
        initPayment();
    }

    public void initPayment() {
        List<CompanyDto> companyDtos = companyService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ContractorDto> contractorDtos = contractorService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ProjectDto> projectDtos = projectService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ContractDto> contractDtos = contractService.getAll().stream().limit(3).collect(Collectors.toList());
        int count = 1;
        for (CompanyDto companyDto : companyDtos) {
            for (ContractorDto contractorDto : contractorDtos) {
                for (ContractDto contractDto : contractDtos) {
                    for (ProjectDto projectDto : projectDtos) {
                        paymentService.create(
                                PaymentDto.builder()
                                        .id(null)
                                        .number("0000" + count)
                                        .sum(BigDecimal.valueOf(100))
                                        .paymentMethods("CASH")
                                        .typeOfPayment("INCOMING")
                                        .time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                        .companyId(companyDto.getId())
                                        .contractorId(contractorDto.getId())
                                        .contractId(contractDto.getId())
                                        .projectId(projectDto.getId())
                                        .build()
                        );
                        count++;

                        paymentService.create(PaymentDto.builder()
                                .id(null)
                                .number("0000" + count)
                                .sum(BigDecimal.valueOf(50))
                                .paymentMethods("BANK")
                                .typeOfPayment("OUTGOING")
                                .time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .companyId(companyDto.getId())
                                .contractorId(contractorDto.getId())
                                .contractId(contractDto.getId())
                                .projectId(projectDto.getId())
                                .build()
                        );
                        count++;
                    }
                }
            }
        }
    }

}
*/
