//package com.trade_accounting.config.init;
//
//import com.trade_accounting.models.TechnicalCardGroup;
//import com.trade_accounting.models.TechnicalCardProduction;
//import com.trade_accounting.models.dto.TechnicalCardDto;
//import com.trade_accounting.models.dto.TechnicalCardGroupDto;
//import com.trade_accounting.models.dto.TechnicalCardProductionDto;
//import com.trade_accounting.repositories.TechnicalCardProductionRepository;
//import com.trade_accounting.services.interfaces.TechnicalCardGroupService;
//import com.trade_accounting.services.interfaces.TechnicalCardService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class TechnicalCardInit extends InitData {
//
//    private final TechnicalCardService technicalCardService;
//    private final TechnicalCardGroupService technicalCardGroupService;
//    private final TechnicalCardProduction;
//
//    @Override
//    void init() {
//        initTechnicalCards();
//    }
//
//    public void initTechnicalCards() {
//        List<TechnicalCardGroupDto> technicalCardGroups = technicalCardGroupService.getAll()
//                .stream().limit(3).collect(Collectors.toList());
//        List<TechnicalCardProduction> finalProduct = technicalCardProductionRepository.findAll()
//                .stream().limit(3).collect(Collectors.toList());
//        List<TechnicalCardProduction> materials =
//        technicalCardService.create(new TechnicalCardDto(
//                null,
//                "Техническая карта №1",
//                "Комментарий1",
//                "1000",
//                technicalCardGroupService.getById(1L),
//                ,
//                List.of(new TechnicalCardProductionDto(null, 2L, 3L),
//                        new TechnicalCardProductionDto(null, 2L, 4L))));
//        technicalCardService.create(new TechnicalCardDto(
//                null,
//                "Техническая карта №2",
//                "Комментарий2",
//                "1100",
//                technicalCardGroupService.getById(1L),
//                List.of(new TechnicalCardProductionDto(null, 2L, 5L),
//                        new TechnicalCardProductionDto(null, 2L, 6L)),
//                List.of(new TechnicalCardProductionDto(null, 2L, 7L),
//                        new TechnicalCardProductionDto(null, 2L, 8L))));
//        technicalCardService.create(new TechnicalCardDto(
//                null,
//                "Техническая карта №3",
//                "Комментарий3",
//                "1200",
//                technicalCardGroupService.getById(2L),
//                List.of(new TechnicalCardProductionDto(null, 2L, 9L),
//                        new TechnicalCardProductionDto(null, 2L, 10L)),
//                List.of(new TechnicalCardProductionDto(null, 2L, 11L),
//                        new TechnicalCardProductionDto(null, 2L, 12L))));
//        technicalCardService.create(new TechnicalCardDto(
//                null,
//                "Техническая карта №4",
//                "Комментарий4",
//                "1300",
//                technicalCardGroupService.getById(2L),
//                List.of(new TechnicalCardProductionDto(null, 2L, 13L),
//                        new TechnicalCardProductionDto(null, 2L, 14L)),
//                List.of(new TechnicalCardProductionDto(null, 2L, 15L),
//                        new TechnicalCardProductionDto(null, 2L, 16L))));
//    }
//}
