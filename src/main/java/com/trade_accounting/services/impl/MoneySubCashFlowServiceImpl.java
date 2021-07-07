package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.MoneySubCashFlowDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.services.interfaces.MoneySubCashFlowService;
import com.trade_accounting.services.interfaces.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class MoneySubCashFlowServiceImpl implements MoneySubCashFlowService {

    private final PaymentService paymentService;

    public MoneySubCashFlowServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public List<MoneySubCashFlowDto> getAll() {
        BigDecimal cashComing;
        BigDecimal cashExpense;
        BigDecimal cashBalance;
        List<PaymentDto> paymentDtos = paymentService.getAll();
        List<PaymentDto> comingPaymentDto = paymentDtos.stream().filter(c -> c.getTypeOfPayment().toString().equals("INCOMING")).collect(Collectors.toList());
        if (comingPaymentDto.isEmpty()){
            cashComing = BigDecimal.valueOf(0.00);
        } else {
            cashComing = comingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        List<PaymentDto> expensePaymentDto = paymentDtos.stream().filter(c -> c.getTypeOfPayment().toString().equals("OUTGOING")).collect(Collectors.toList());
        if (expensePaymentDto.isEmpty()){
            cashExpense = BigDecimal.ZERO;
        } else {
            cashExpense = expensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        cashBalance = cashComing.subtract(cashExpense);

        // Заглушка для банковских операций
        BigDecimal bankComing = BigDecimal.ZERO;
        BigDecimal bankExpense = BigDecimal.ZERO;
        BigDecimal bankBalance = BigDecimal.ZERO;

        BigDecimal allComing = cashComing.add(bankComing);
        BigDecimal allExpense = cashExpense.add(bankExpense);
        BigDecimal allBalance = cashBalance.add(bankBalance);

        MoneySubCashFlowDto moneySubCashFlowDto = new MoneySubCashFlowDto(1L,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(),
                bankComing,bankExpense,bankBalance,cashComing,cashExpense,cashBalance,allComing,allExpense,allBalance);
        List<MoneySubCashFlowDto> moneySubCashFlowDtoList = new ArrayList<>();
        moneySubCashFlowDtoList.add(moneySubCashFlowDto);
        return moneySubCashFlowDtoList;
    }
}
