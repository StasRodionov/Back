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
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class TechnicalCardInit extends InitData {
//
//    private final TechnicalCardService technicalCardService;
//    private final TechnicalCardGroupService technicalCardGroupService;
//    private final TechnicalCardProductionRepository technicalCardProductionRepository;
//
//    @Override
//    void init() {
//        initTechnicalCards();
//    }
//
//    public void initTechnicalCards() {
//        List<TechnicalCardGroupDto> technicalCardGroups = technicalCardGroupService.getAll()
//                .stream().limit(3).collect(Collectors.toList());
//        int count = 1;
//        int count2 = 100;
//        for (TechnicalCardGroupDto technicalCardGroupDto : technicalCardGroups) {
//            technicalCardService.create(
//                    TechnicalCardDto.builder()
//                            .id(null)
//                            .name("Техническая карта №" + count)
//                            .comment("Комментарий" + count)
//                            .productionCost("1000" + count2)
//                            .technicalCardGroupId(technicalCardGroupDto.getId())
//                            .finalProductionId(List.of(
//                                    new TechnicalCardProductionDto(1L, 2L, 1L).getId(),
//                                    new TechnicalCardProductionDto(2L, 2L, 2L).getId(),
//                                    new TechnicalCardProductionDto(3L, 2L, 3L).getId()))
//                            .materialsId(List.of(
//                                    new TechnicalCardProductionDto(4L, 2L, 4L).getId(),
//                                    new TechnicalCardProductionDto(5L, 2L, 5L).getId(),
//                                    new TechnicalCardProductionDto(6L, 2L, 6L).getId()))
//                            .build()
//            );
//            count++;
//            count2 = count2 + 100;
//        }
//    }
//}
