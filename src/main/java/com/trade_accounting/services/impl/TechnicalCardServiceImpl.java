package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.TechnicalCardGroup;
import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.repositories.TechnicalCardGroupRepository;
import com.trade_accounting.repositories.TechnicalCardProductionRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.services.interfaces.TechnicalCardService;
import com.trade_accounting.utils.mapper.TechnicalCardGroupMapper;
import com.trade_accounting.utils.mapper.TechnicalCardMapper;
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
    private final TechnicalCardMapper technicalCardMapper;
    private final TechnicalCardGroupRepository technicalCardGroupRepository;

    @Override
    public List<TechnicalCardDto> getAll() {
        return technicalCardRepository.findAll().stream()
                .map(technicalCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalCardDto getById(Long id) {
        return technicalCardMapper.toDto(
                technicalCardRepository.getOne(id));
    }

    @Override
    public TechnicalCardDto create(TechnicalCardDto technicalCardDto) {
        TechnicalCard technicalCard = technicalCardMapper.toModel(technicalCardDto);

        technicalCard.setTechnicalCardGroup(
                technicalCardGroupRepository.findById(
                        technicalCardDto.getTechnicalCardGroupId()
                ).orElse(null)
        );

        technicalCard.setFinalProduction(
                technicalCardProductionRepository.findAllById(
                        technicalCardDto.getFinalProductionId()
                )
        );

        technicalCard.setMaterials(
                technicalCardProductionRepository.findAllById(
                        technicalCardDto.getMaterialsId()
                )
        );

        return technicalCardMapper.toDto(technicalCardRepository.save(technicalCard));
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
                    .map(technicalCardMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return technicalCardRepository.search(searchTerm).stream()
                    .map(technicalCardMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<TechnicalCardDto> getAllByTechnicalCardGroupId(Long id) {
        return technicalCardRepository.getAllByTechnicalCardGroupId(id).stream()
                .map(technicalCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TechnicalCardDto> search(Specification<TechnicalCard> spec) {
        return executeSearch(technicalCardRepository, technicalCardMapper::toDto, spec);
    }

}
