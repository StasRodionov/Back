package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.MoneySubProfitLossDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.services.interfaces.LossProductService;
import com.trade_accounting.services.interfaces.MoneySubProfitLossService;
import com.trade_accounting.services.interfaces.PaymentService;
import com.trade_accounting.services.interfaces.ShipmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class MoneySubProfitLossServiceImpl implements MoneySubProfitLossService {

    private final PaymentService paymentService;
    private final LossProductService lossProductService;
    private final ShipmentService shipmentService;

    public MoneySubProfitLossServiceImpl(PaymentService paymentService, LossProductService lossProductService, ShipmentService shipmentService) {
        this.paymentService = paymentService;
        this.lossProductService = lossProductService;
        this.shipmentService = shipmentService;
    }

    @Override
    public MoneySubProfitLossDto getMoneySubProfitLossDto() {
        MoneySubProfitLossDto moneySubProfitLossDto = new MoneySubProfitLossDto();
        BigDecimal revenue = BigDecimal.ZERO;
        BigDecimal costPrice = BigDecimal.ZERO;
        BigDecimal grossProfit;
        BigDecimal operatingExpenses = BigDecimal.ZERO;
        BigDecimal writeOffs;
        BigDecimal rental;
        BigDecimal salary;
        BigDecimal marketing;
        BigDecimal operatingProfit;
        BigDecimal taxesAndFees;

        //Revenue (Выручка) = отгрузки + розничные продажи − возвраты

        //Cost price (Себестоимость) = закупочная цена учтенного товар

        //Gross profit (Валовая прибыль)
        grossProfit = revenue.subtract(costPrice);
        moneySubProfitLossDto.setGrossProfit(grossProfit);

        //Write-offs (Списания)
        writeOffs = lossProductService.getAll().stream()
                .map(l -> l.getPrice().multiply(l.getAmount()))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        moneySubProfitLossDto.setWriteOffs(writeOffs);

        //Rental (Аренда)
        rental = getPaymentSumByParam("RENTAL");
        moneySubProfitLossDto.setRental(rental);

        //Salary (Зарплата)
        salary = getPaymentSumByParam("SALARY");
        moneySubProfitLossDto.setSalary(salary);

        //Marketing (Маркетинг и реклама)
        marketing = getPaymentSumByParam("MARKETING");
        moneySubProfitLossDto.setMarketing(marketing);

        //Operating expenses (Операционные расходы) =  аренда + зарплата + маркетинг и реклама
        operatingExpenses = operatingExpenses.add(writeOffs).add(rental).add(salary).add(marketing);
        moneySubProfitLossDto.setOperatingExpenses(operatingExpenses);

        //Operating profit (Операционная прибыль) = валовая прибыль − операционные расходы
        operatingProfit = grossProfit.subtract(operatingExpenses);
        moneySubProfitLossDto.setOperatingProfit(operatingProfit);

        //Taxes and fees (Налоги и сборы) = сумма расходных документов со статьей «Налоги и сборы»
        taxesAndFees = getPaymentSumByParam("TAXESANDFEES");
        moneySubProfitLossDto.setTaxesAndFees(taxesAndFees);

        //Net profit (Чистая прибыль) = операционная прибыль − cумма налогов и сборов
        moneySubProfitLossDto.setNetProfit(operatingProfit.subtract(taxesAndFees));

        return moneySubProfitLossDto;
    }

    // Получение суммы расходного платежа по его типу
    private BigDecimal getPaymentSumByParam(String param) {

        return paymentService.getAll().stream()
                .filter(x -> x.getTypeOfPayment().equals("OUTGOING"))
                .filter(q -> q.getExpenseItem().equals(param))
                .map(PaymentDto::getSum)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
