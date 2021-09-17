package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.MoneySubProfitLossDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.services.interfaces.LossProductService;
import com.trade_accounting.services.interfaces.MoneySubProfitLossService;
import com.trade_accounting.services.interfaces.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoneySubProfitLossServiceImpl implements MoneySubProfitLossService {

    private final PaymentService paymentService;
    private final LossProductService lossProductService;

    public MoneySubProfitLossServiceImpl(PaymentService paymentService, LossProductService lossProductService) {
        this.paymentService = paymentService;
        this.lossProductService = lossProductService;
    }

    @Override
    public MoneySubProfitLossDto getMoneySubProfitLossDto() {
        MoneySubProfitLossDto moneySubProfitLossDto = new MoneySubProfitLossDto();
        BigDecimal revenue = BigDecimal.ZERO;
        BigDecimal costPrice = BigDecimal.ZERO;
        BigDecimal grossProfit = BigDecimal.ZERO;
        BigDecimal operatingExpenses = BigDecimal.ZERO;
        BigDecimal writeOffs;
        BigDecimal rental;
        BigDecimal salary;
        BigDecimal marketing;
        BigDecimal operatingProfit = BigDecimal.ZERO;
        BigDecimal taxesAndFees = BigDecimal.ZERO;
        BigDecimal netProfit = BigDecimal.ZERO;

        //Revenue

        //Cost price

        //Gross profit

        //Write-offs
        writeOffs = lossProductService.getAll().stream()
                .map(l -> l.getPrice().multiply(l.getAmount()))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        moneySubProfitLossDto.setWriteOffs(writeOffs);

        //Rental
        rental = getPaymentSumByParam("RENTAL");
        moneySubProfitLossDto.setRental(rental);

        //Salary
        salary = getPaymentSumByParam("SALARY");
        moneySubProfitLossDto.setSalary(salary);

        //Marketing
        marketing = getPaymentSumByParam("MARKETING");
        moneySubProfitLossDto.setMarketing(marketing);

        //Operating expenses
        operatingExpenses = operatingExpenses.add(writeOffs).add(rental).add(salary).add(marketing);
        moneySubProfitLossDto.setOperatingExpenses(operatingExpenses);

        //Operating profit

        //Taxes and fees

        //Net profit

        return moneySubProfitLossDto;
    }

    private BigDecimal getPaymentSumByParam(String param) {

        return paymentService.getAll().stream()
                .filter(x -> x.getTypeOfPayment().equals("OUTGOING"))
                .filter(q -> q.getExpenseItem().equals(param))
                .map(PaymentDto::getSum)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
