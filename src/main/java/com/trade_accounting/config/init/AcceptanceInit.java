//package com.trade_accounting.config.init;
//
//import com.trade_accounting.models.dto.AcceptanceDto;
//import com.trade_accounting.services.interfaces.AcceptanceService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//@Component
//@RequiredArgsConstructor
//public class AcceptanceInit extends InitData {
//
//    private final AcceptanceService acceptanceService;
//
//    @Override
//    void init() {
//        initAcceptance();
//    }
//
//    public void initAcceptance() {
//        for (long i = 1L; i <= 4; i ++) {
//            acceptanceService.create(AcceptanceDto.builder()
//                .id(i)
//                .incomingNumber("100" + i)
//                .comment("Комментарий " + i)
//                .incomingNumberDate(LocalDate.now().toString())
//                .contractorId(i)
//                .warehouseId(i)
//                .contractId(i)
//                .isSent(false)
//                .isPrint(false)
//                .acceptanceProduction(new ArrayList<>())
//                .build()
//            );
//        }
//    }
//}
