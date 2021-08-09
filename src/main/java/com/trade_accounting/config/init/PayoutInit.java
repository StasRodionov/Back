//package com.trade_accounting.config.init;
//
//import com.trade_accounting.models.dto.PayoutDto;
//import com.trade_accounting.services.interfaces.PayoutService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//public class PayoutInit extends InitData{
//
//    private final PayoutService payoutService;
//
//    @Override
//    void init() {
//        initPayout();
//    }
//
//    public void initPayout() {
//
//        payoutService.create(PayoutDto.builder()
//                .id(1L)
//                .date(LocalDateTime.now().toString())
//                .retailStoreId(1L)
//                .whoWasPaid("Сергеев Петр Сергеевич")
//                .companyId(1L)
//                .isSent(true)
//                .isPrint(true)
//                .comment("Коммент")
//                .build());
//
//        payoutService.create(PayoutDto.builder()
//                .id(2L)
//                .date(LocalDateTime.now().toString())
//                .retailStoreId(2L)
//                .whoWasPaid("Стрелецкая Анастасия Михайловна")
//                .companyId(6L)
//                .isSent(false)
//                .isPrint(true)
//                .comment("Комментарий")
//                .build());
//
//        payoutService.create(PayoutDto.builder()
//                .id(3L)
//                .date(LocalDateTime.now().toString())
//                .retailStoreId(3L)
//                .whoWasPaid("Стрелецкая Анастасия Михайловна")
//                .companyId(9L)
//                .isSent(true)
//                .isPrint(false)
//                .comment("Комментарий комментария")
//                .build());
//    }
//
//}
