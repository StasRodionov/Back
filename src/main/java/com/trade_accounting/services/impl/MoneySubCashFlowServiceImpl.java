package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.MoneySubCashFlowDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.services.interfaces.MoneySubCashFlowService;
import com.trade_accounting.services.interfaces.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        List<MoneySubCashFlowDto> moneySubCashFlowDtoList = new ArrayList<>();
        BigDecimal cashComing;
        BigDecimal cashExpense;
        BigDecimal cashBalance;
        BigDecimal finalCashComing;
        BigDecimal finalCashExpense;
        BigDecimal finalCashBalance;
        Long count = 0L;

        //Создание начального остатка
        moneySubCashFlowDtoList.add(new MoneySubCashFlowDto(0L,"Начальный остаток",BigDecimal.ZERO,BigDecimal.ZERO
                ,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO));

        //Получение всех платежей
        List<PaymentDto> paymentDtos = paymentService.getAll();

        //Получение Листа с датами платежей
        List date = paymentDtos.stream().map(c -> c.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).distinct().sorted().collect(Collectors.toList());

        //Добавление Платежей внутри одной даты
        for (Object i:date) {

            List<PaymentDto> comingPaymentDto = paymentDtos.stream().filter(t -> t.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(i.toString()))
                    .filter(c -> c.getTypeOfPayment().toString().equals("INCOMING")).collect(Collectors.toList());
            if (comingPaymentDto.isEmpty()) {
                cashComing = BigDecimal.ZERO;
            } else {
                cashComing = comingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            List<PaymentDto> expensePaymentDto = paymentDtos.stream().filter(t -> t.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(i.toString()))
                    .filter(c -> c.getTypeOfPayment().toString().equals("OUTGOING")).collect(Collectors.toList());
            if (expensePaymentDto.isEmpty()) {
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

            MoneySubCashFlowDto moneySubCashFlowDto = new MoneySubCashFlowDto(count++,
                    i.toString(),bankComing, bankExpense, bankBalance,
                    cashComing, cashExpense, cashBalance, allComing, allExpense, allBalance);

            moneySubCashFlowDtoList.add(moneySubCashFlowDto);
        }

        //Получение конечного остатка
        List<PaymentDto> comingPaymentDto = paymentDtos.stream()
                .filter(c -> c.getTypeOfPayment().toString().equals("INCOMING")).collect(Collectors.toList());
        if (comingPaymentDto.isEmpty()) {
            finalCashComing = BigDecimal.ZERO;
        } else {
            finalCashComing = comingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        List<PaymentDto> expensePaymentDto = paymentDtos.stream()
                .filter(c -> c.getTypeOfPayment().toString().equals("OUTGOING")).collect(Collectors.toList());
        if (expensePaymentDto.isEmpty()) {
            finalCashExpense = BigDecimal.ZERO;
        } else {
            finalCashExpense = expensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        finalCashBalance = finalCashComing.subtract(finalCashExpense);

        // Заглушка для банковских операций
        BigDecimal finalBankComing = BigDecimal.ZERO;
        BigDecimal finalBankExpense = BigDecimal.ZERO;
        BigDecimal finalBankBalance = BigDecimal.ZERO;

        BigDecimal finalAllComing = finalCashComing.add(finalBankComing);
        BigDecimal finalAllExpense = finalCashExpense.add(finalBankExpense);
        BigDecimal finalAllBalance = finalCashBalance.add(finalBankBalance);

        moneySubCashFlowDtoList.add(new MoneySubCashFlowDto(Long.MAX_VALUE,"Конечный остаток",finalBankComing,finalBankExpense,
                finalBankBalance,finalCashComing,finalCashExpense,finalCashBalance,finalAllComing,finalAllExpense,finalAllBalance));

        return moneySubCashFlowDtoList;
    }
}
