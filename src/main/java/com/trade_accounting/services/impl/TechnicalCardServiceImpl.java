package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.repositories.TechnicalCardProductionRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.services.interfaces.TechnicalCardService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.mapper.TechnicalCardProductionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicalCardServiceImpl implements TechnicalCardService {

    private final TechnicalCardRepository technicalCardRepository;
    private final TechnicalCardProductionRepository technicalCardProductionRepository;
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;
    private final TechnicalCardProductionMapper technicalCardProductionMapper;

    @Override
    public List<TechnicalCardDto> getAll() {
        return technicalCardRepository.findAll().stream()
                .map(dtoMapper::technicalCardToTechnicalCardDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalCardDto getById(Long id) {
        return dtoMapper.technicalCardToTechnicalCardDto(
                technicalCardRepository.getOne(id));
    }

    @Override
    public TechnicalCardDto create(TechnicalCardDto dto) {
        TechnicalCard technicalCard = TechnicalCard.builder().id(dto.getId()).name(dto.getName())
                .comment(dto.getComment()).productionCost(dto.getProductionCost())
                .technicalCardGroup(dtoMapper.technicalCardGroupDtoToTechnicalCardGroup(dto.getTechnicalCardGroupDto()))
                .build();

        List<TechnicalCardProduction> finalProduction = dto.getFinalProductionDto().stream()
                .map(x -> {
                    TechnicalCardProduction tcp =
                            technicalCardProductionMapper.toModel(x);
                    tcp.setProduct(productRepository.getOne(x.getProductId()));
                    return tcp;
                }).collect(Collectors.toList());
        finalProduction.stream().forEach(technicalCardProductionRepository::save);
        technicalCard.setFinalProduction(finalProduction);

        List<TechnicalCardProduction> materials = dto.getMaterialsDto().stream()
                .map(x -> {
                    TechnicalCardProduction tcp =
                            technicalCardProductionMapper.toModel(x);
                    tcp.setProduct(productRepository.getOne(x.getProductId()));
                    return tcp;
                }).collect(Collectors.toList());
        materials.stream().forEach(technicalCardProductionRepository::save);
        technicalCard.setMaterials(materials);

        return dtoMapper.technicalCardToTechnicalCardDto(technicalCardRepository.save(technicalCard));
    }

    @Override
    public TechnicalCardDto update(TechnicalCardDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        technicalCardRepository.deleteById(id);
    }

    @Override
    public List<TechnicalCardDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            return technicalCardRepository.findAll().stream()
                    .map(dtoMapper::technicalCardToTechnicalCardDto)
                    .collect(Collectors.toList());
        } else {
            return technicalCardRepository.search(searchTerm).stream()
                    .map(dtoMapper::technicalCardToTechnicalCardDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<TechnicalCardDto> getAllByTechnicalCardGroupId(Long id) {
        return technicalCardRepository.getAllByTechnicalCardGroupId(id).stream()
                .map(dtoMapper::technicalCardToTechnicalCardDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TechnicalCardDto> search(Specification<TechnicalCard> spec) {
        return executeSearch(technicalCardRepository, dtoMapper::technicalCardToTechnicalCardDto, spec);
    }

}
