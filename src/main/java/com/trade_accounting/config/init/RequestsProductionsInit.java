package com.trade_accounting.config.init;

import com.trade_accounting.models.dto.RequestsProductionsDto;
import com.trade_accounting.services.interfaces.RequestsProductionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class RequestsProductionsInit extends InitData {

    private final RequestsProductionsService requestsProductionsService;

    @Override
    public void init() {
        requestsProductionsService.create(RequestsProductionsDto.builder()
                .id(null)
                .numberOfTheCertificate("RP-001")
                .dateOfTheCertificate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .technicalCardId(1L)
                .volume(500)
                .warehouseId(1L)
                .build());

        requestsProductionsService.create(RequestsProductionsDto.builder()
                .id(null)
                .numberOfTheCertificate("RP-002")
                .dateOfTheCertificate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .technicalCardId(2L)
                .volume(1000)
                .warehouseId(2L)
                .build());

        requestsProductionsService.create(RequestsProductionsDto.builder()
                .id(null)
                .numberOfTheCertificate("RP-003")
                .dateOfTheCertificate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .technicalCardId(2L)
                .volume(720)
                .warehouseId(1L)
                .build());
    }
}
