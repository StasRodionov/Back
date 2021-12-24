package com.trade_accounting.services.impl;

import com.trade_accounting.models.AcceptanceProduction;
import com.trade_accounting.models.ShipmentProduct;
import com.trade_accounting.models.dto.MoneySubProfitLossDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.RetailReturnDto;
import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.repositories.AcceptanceProductionRepository;
import com.trade_accounting.repositories.ShipmentProductRepository;
import com.trade_accounting.services.interfaces.AcceptanceService;
import com.trade_accounting.services.interfaces.LossProductService;
import com.trade_accounting.services.interfaces.MoneySubProfitLossService;
import com.trade_accounting.services.interfaces.PaymentService;
import com.trade_accounting.services.interfaces.RetailReturnService;
import com.trade_accounting.services.interfaces.RetailSalesService;
import com.trade_accounting.services.interfaces.ShipmentService;
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
    private final ShipmentService shipmentService;
    private final ShipmentProductRepository shipmentProductRepository;
    private final RetailReturnService retailReturnService;
    private final RetailSalesService retailSalesService;
    private final AcceptanceService acceptanceService;
    private final AcceptanceProductionRepository acceptanceProductionRepository;

    public MoneySubProfitLossServiceImpl(PaymentService paymentService, LossProductService lossProductService,
                                         ShipmentService shipmentService, ShipmentProductRepository shipmentProductRepository,
                                         RetailReturnService retailReturnService, RetailSalesService retailSalesService,
                                         AcceptanceService acceptanceService, AcceptanceProductionRepository acceptanceProductionRepository) {
        this.paymentService = paymentService;
        this.lossProductService = lossProductService;
        this.shipmentService = shipmentService;
        this.shipmentProductRepository = shipmentProductRepository;
        this.retailReturnService = retailReturnService;
        this.retailSalesService = retailSalesService;
        this.acceptanceService = acceptanceService;
        this.acceptanceProductionRepository = acceptanceProductionRepository;
    }

    @Override
    public MoneySubProfitLossDto getMoneySubProfitLossDto() {
        MoneySubProfitLossDto moneySPL = new MoneySubProfitLossDto();
        BigDecimal revenue;
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
        revenue = shipmentService.getAll().stream()
                .flatMap(x -> x.getShipmentProductsIds().stream())
                .map(shipmentProductRepository::getOne)
                .map(x -> x.getAmount().multiply(x.getPrice()))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        BigDecimal retailSalesSum = retailSalesService.getAll().stream()
                .map(RetailSalesDto::getSum)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        BigDecimal retailReturnsSum = retailReturnService.getAll().stream()
                .map(RetailReturnDto::getTotalAmount)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        revenue = revenue.add(retailSalesSum).subtract(retailReturnsSum);

        moneySPL.setRevenue(revenue);

        //Cost price (Себестоимость) = закупочная цена учтенного товара

        List<ShipmentProduct> shimpentProducts = shipmentService.getAll().stream()
                .flatMap(x -> x.getShipmentProductsIds().stream())
                .map(shipmentProductRepository::getOne)
                .collect(Collectors.toList());

        List<AcceptanceProduction> acceptanceProductions = acceptanceService.getAll().stream()
                .flatMap(x -> x.getAcceptanceProduction().stream())
                .map(x -> acceptanceProductionRepository.getOne(x.getId()))
                .collect(Collectors.toList());

        for (AcceptanceProduction acceptanceProduction : acceptanceProductions) {
          costPrice = costPrice.add(shimpentProducts.stream()
                    .filter(x -> x.getProduct().equals(acceptanceProduction.getProduct()))
                    .map(x -> x.getAmount().multiply(acceptanceProduction.getPrice()))
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        }
        moneySPL.setCostPrice(costPrice);

        //Gross profit (Валовая прибыль)
        grossProfit = revenue.subtract(costPrice);
        moneySPL.setGrossProfit(grossProfit);

        //Write-offs (Списания)
        writeOffs = lossProductService.getAll().stream()
                .map(l -> l.getPrice().multiply(l.getAmount()))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        moneySPL.setWriteOffs(writeOffs);

        //Rental (Аренда)
        rental = getPaymentSumByParam("RENTAL");
        moneySPL.setRental(rental);

        //Salary (Зарплата)
        salary = getPaymentSumByParam("SALARY");
        moneySPL.setSalary(salary);

        //Marketing (Маркетинг и реклама)
        marketing = getPaymentSumByParam("MARKETING");
        moneySPL.setMarketing(marketing);

        //Operating expenses (Операционные расходы) =  аренда + зарплата + маркетинг и реклама
        operatingExpenses = operatingExpenses.add(writeOffs).add(rental).add(salary).add(marketing);
        moneySPL.setOperatingExpenses(operatingExpenses);

        //Operating profit (Операционная прибыль) = валовая прибыль − операционные расходы
        operatingProfit = grossProfit.subtract(operatingExpenses);
        moneySPL.setOperatingProfit(operatingProfit);

        //Taxes and fees (Налоги и сборы) = сумма расходных документов со статьей «Налоги и сборы»
        taxesAndFees = getPaymentSumByParam("TAXESANDFEES");
        moneySPL.setTaxesAndFees(taxesAndFees);

        //Net profit (Чистая прибыль) = операционная прибыль − cумма налогов и сборов
        moneySPL.setNetProfit(operatingProfit.subtract(taxesAndFees));

        return moneySPL;
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
