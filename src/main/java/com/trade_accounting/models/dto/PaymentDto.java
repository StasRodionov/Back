package com.trade_accounting.models.dto;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Project;
import com.trade_accounting.models.TypeOfPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Company company;

    @NotNull
    private Contractor contractor;

    private Contract contract;

    //Переделать в enum
    @NotNull
    private String costItem;

    private Project project;

    private BigDecimal sum;
}
