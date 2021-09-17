package com.trade_accounting.services.impl;

import com.trade_accounting.models.TypeOfPayment;
import com.trade_accounting.models.dto.MoneySubProfitLossDto;
import com.trade_accounting.services.interfaces.MoneySubProfitLossService;
import com.trade_accounting.services.interfaces.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MoneySubProfitLossServiceImpl implements MoneySubProfitLossService {

    private final PaymentService paymentService;

    public MoneySubProfitLossServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public MoneySubProfitLossDto getMoneySubProfitLossDto() {
        MoneySubProfitLossDto moneySubProfitLossDto = new MoneySubProfitLossDto();
        BigDecimal revenue = BigDecimal.ZERO;
        BigDecimal costPrice = BigDecimal.ZERO;
        BigDecimal grossProfit = BigDecimal.ZERO;
        BigDecimal operatingExpenses = BigDecimal.ZERO;
        BigDecimal writeOffs = BigDecimal.ZERO;
        BigDecimal rental = BigDecimal.ZERO;
        BigDecimal salary = BigDecimal.ZERO;
        BigDecimal marketing = BigDecimal.ZERO;
        BigDecimal operatingProfit = BigDecimal.ZERO;
        BigDecimal taxesAndFees = BigDecimal.ZERO;
        BigDecimal netProfit = BigDecimal.ZERO;

        //Revenue

        //Cost price
        List<BigDecimal> decimalList = new ArrayList<>();
        paymentService.getAll().stream()
                .filter(x -> x.getTypeOfPayment().toString().equals("OUTGOING"))
                .forEach(q -> decimalList.add(q.getSum()));

        for (BigDecimal decimal : decimalList) {
            costPrice = costPrice.add(decimal);
        }
        moneySubProfitLossDto.setCostPrice(costPrice);

        //Gross profit

        //Write-offs

        //Rental

        //Salary

        //Marketing

        //Operating expenses
        operatingExpenses = operatingExpenses.add(writeOffs).add(rental).add(salary).add(marketing);
        moneySubProfitLossDto.setOperatingExpenses(operatingExpenses);

        //Operating profit

        //Taxes and fees

        //Net profit

        return moneySubProfitLossDto;
    }
}
