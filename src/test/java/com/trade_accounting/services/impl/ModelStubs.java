package com.trade_accounting.services.impl;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.Payment;
import com.trade_accounting.models.Project;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfPayment;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.LegalDetailDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelStubs {
    //TODO Вынести заглушки моделей из классов сервисов сюда

    public static Payment getPayment(Long id) {
        return new Payment(
                id, TypeOfPayment.INCOMING,
                "00001", LocalDateTime.now(),
                getCompany(id), getContractor(id),
                getContract(id), getProject(id),
                BigDecimal.ONE
        );
    }

    public static Company getCompany(Long id) {
        return new Company(
                id, "name",
                "inn", "00001",
                "89040408488", "3420943",
                "email", true, "address",
                "commentToAddress", "leader",
                "leaderManagerPos", "signatureOfLider",
                "cheidAcc", "aaaaa", "stamp",
                getLegalDetail(id),
                Stream.of(
                        getBankAccount(1L),
                        getBankAccount(2L),
                        getBankAccount(3L)
                ).collect(Collectors.toList())
        );
    }

    public static Contractor getContractor(Long id) {
        return new Contractor(
                id, "name",
                "123456789012", "sortNumber",
                "12345678901", "324234234",
                "email", "address",
                "commentToAddress", "comment",
                getContractorGroup(id), getTypeOfContractor(id),
                getTypeOfPrice(id),
                Stream.of(
                        getBankAccount(1L),
                        getBankAccount(2L),
                        getBankAccount(3L)
                ).collect(Collectors.toList()),
                getLegalDetail(id)
        );
    }

    public static Contract getContract(Long id) {
        return new Contract(
                id, "00001",
                LocalDate.now(), getCompany(id),
                getBankAccount(id), getContractor(id),
                BigDecimal.ONE, false, "comment",
                getLegalDetail(id)
        );
    }

    public static Project getProject(Long id) {
        return new Project(
                id, "name",
                "00001", "description"
        );
    }

    public static BankAccount getBankAccount(Long id) {
        return new BankAccount(
                id, "rbic", "bank",
                "address", "correspon",
                "account", true, "00001"
        );
    }

    public static ContractorGroup getContractorGroup(Long id) {
        return new ContractorGroup(id, "name", "00001");
    }

    public static TypeOfContractor getTypeOfContractor(Long id) {
        return new TypeOfContractor(id, "name", "00001");
    }

    public static TypeOfPrice getTypeOfPrice(Long id) {
        return new TypeOfPrice(id, "name", "00001");
    }

    public static LegalDetail getLegalDetail(Long id) {
        return new LegalDetail(
                id, "lastName",
                "firstNAme", "middleName",
                "address", "commentToAddress",
                "32432423", "okpo", "ogrnip",
                "numberOfCertifacate", LocalDate.now(),
                getTypeOfContractor(id)
        );
    }
}
