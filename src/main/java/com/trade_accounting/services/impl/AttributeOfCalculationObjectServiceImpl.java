package com.trade_accounting.services.impl;

import com.trade_accounting.models.AttributeOfCalculationObject;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.repositories.AttributeOfCalculationObjectRepository;
import com.trade_accounting.services.interfaces.AttributeOfCalculationObjectService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttributeOfCalculationObjectServiceImpl implements AttributeOfCalculationObjectService {

    private final AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;

    private final DtoMapper dtoMapper;

    public AttributeOfCalculationObjectServiceImpl(AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository, DtoMapper dtoMapper) {
        this.attributeOfCalculationObjectRepository = attributeOfCalculationObjectRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<AttributeOfCalculationObjectDto> getAll() {
        return attributeOfCalculationObjectRepository.findAll().stream()
                .map(dtoMapper::attributeOfCalculationObjectToAttributeOfCalculationObjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttributeOfCalculationObjectDto getById(Long id) {
        return dtoMapper.attributeOfCalculationObjectToAttributeOfCalculationObjectDto(
                attributeOfCalculationObjectRepository.findById(id).orElse(new AttributeOfCalculationObject())
        );
    }

    @Override
    public AttributeOfCalculationObjectDto create(AttributeOfCalculationObjectDto attribute) {
        AttributeOfCalculationObject savedAttribute = attributeOfCalculationObjectRepository.save(
                dtoMapper.attributeOfCalculationObjectDtoToAttributeOfCalculationObject(attribute)
        );

        return dtoMapper.attributeOfCalculationObjectToAttributeOfCalculationObjectDto(
                savedAttribute
        );
    }

    @Override
    public AttributeOfCalculationObjectDto update(AttributeOfCalculationObjectDto attribute) {
        return create(attribute);
    }

    @Override
    public void deleteById(Long id) {
        attributeOfCalculationObjectRepository.deleteById(id);
    }
}
