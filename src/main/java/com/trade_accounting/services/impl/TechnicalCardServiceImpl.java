package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.repositories.TechnicalCardProductionRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.services.interfaces.TechnicalCardService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TechnicalCardServiceImpl implements TechnicalCardService {

    private final TechnicalCardRepository technicalCardRepository;
    private final TechnicalCardProductionRepository technicalCardProductionRepository;
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;

    public TechnicalCardServiceImpl(TechnicalCardRepository technicalCardRepository, TechnicalCardProductionRepository technicalCardProductionRepository, ProductRepository productRepository, DtoMapper dtoMapper) {
        this.technicalCardRepository = technicalCardRepository;
        this.technicalCardProductionRepository = technicalCardProductionRepository;
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<TechnicalCardDto> getAll() {
        return technicalCardRepository.findAll().stream()
                .map(dtoMapper::technicalCardToTechnicalCardDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalCardDto getById(Long id) {
        return dtoMapper.technicalCardToTechnicalCardDto(
                technicalCardRepository.findById(id).orElse(new TechnicalCard())
        );
    }

    @Override
    public TechnicalCardDto create(TechnicalCardDto dto) {
        TechnicalCard technicalCard = TechnicalCard.builder().id(dto.getId()).name(dto.getName()).comment(dto.getComment())
                .productionCost(dto.getProductionCost()).sortNumber(dto.getSortNumber())
                .technicalCardGroup(dtoMapper.technicalCardGroupDtoToTechnicalCardGroup(dto.getTechnicalCardGroupDto()))
                .build();

        List<TechnicalCardProduction> finalProduction = dto.getFinalProductionDto().stream()
                .map(dtoMapper::technicalCardProductionDtoToTechnicalCardProduction).collect(Collectors.toList());
        finalProduction.stream().forEach(fp -> {fp.setProduct(productRepository.save(fp.getProduct()));
                                                technicalCardProductionRepository.save(fp);});
        technicalCard.setFinalProduction(finalProduction);

        List<TechnicalCardProduction> materials = dto.getMaterialsDto().stream()
                .map(dtoMapper::technicalCardProductionDtoToTechnicalCardProduction).collect(Collectors.toList());
        materials.stream().forEach(m -> {m.setProduct(productRepository.save(m.getProduct()));
                                        technicalCardProductionRepository.save(m);});
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
        return technicalCardRepository.search(searchTerm).stream()
                .map(dtoMapper::technicalCardToTechnicalCardDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TechnicalCardDto> getAllByTechnicalCardGroupId(Long id) {
        return technicalCardRepository.getAllByTechnicalCardGroupId(id).stream()
                .map(dtoMapper::technicalCardToTechnicalCardDto)
                .collect(Collectors.toList());
    }
}
