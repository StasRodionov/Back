package com.trade_accounting.config.init;

import com.trade_accounting.models.TechnicalCardGroup;
import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.models.dto.TechnicalCardGroupDto;
import com.trade_accounting.models.dto.TechnicalCardProductionDto;
import com.trade_accounting.repositories.TechnicalCardProductionRepository;
import com.trade_accounting.services.interfaces.TechnicalCardGroupService;
import com.trade_accounting.services.interfaces.TechnicalCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TechnicalCardInit extends InitData {

    private final TechnicalCardService technicalCardService;
    private final TechnicalCardGroupService technicalCardGroupService;
    private final TechnicalCardProductionRepository technicalCardProductionRepository;

    @Override
    void init() {
        initTechnicalCards();
    }

    public void initTechnicalCards() {
        List<TechnicalCardGroupDto> technicalCardGroups = technicalCardGroupService.getAll()
                .stream().limit(3).collect(Collectors.toList());
        List<TechnicalCardProduction> finalProducts = technicalCardProductionRepository.findAll()
                .stream().limit(3).collect(Collectors.toList());
        List<Long> listFinalProducts = new ArrayList<>();
        for (TechnicalCardProduction finalProduct : finalProducts) {
            listFinalProducts.add(finalProduct.getId());
        }
        List<TechnicalCardProduction> materials = technicalCardProductionRepository.findAll()
                .stream().limit(3).collect(Collectors.toList());
        List<Long> listMaterials = new ArrayList<>();
        for (TechnicalCardProduction material : materials) {
            listMaterials.add(material.getId());
        }
        int count = 1;
        int count2 = 100;
        for (TechnicalCardGroupDto technicalCardGroupDto : technicalCardGroups) {
            technicalCardService.create(
                    TechnicalCardDto.builder()
                            .id(null)
                            .name("Техническая карта №" + count)
                            .comment("Комментарий" + count)
                            .productionCost("1000")
                            .technicalCardGroupId(technicalCardGroupDto.getId())
                            .finalProductionId(listFinalProducts)
                            .materialsId(listMaterials)
                            .build()
            );
            count++;
            count2++;
        }
    }
}
