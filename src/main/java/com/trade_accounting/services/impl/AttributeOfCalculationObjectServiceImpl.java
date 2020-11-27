package com.trade_accounting.services.impl;

import com.trade_accounting.models.AttributeOfCalculationObject;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.repositories.AttributeOfCalculationObjectRepository;
import com.trade_accounting.services.interfaces.AttributeOfCalculationObjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttributeOfCalculationObjectServiceImpl implements AttributeOfCalculationObjectService {

    private final AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;

    public AttributeOfCalculationObjectServiceImpl(AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository) {
        this.attributeOfCalculationObjectRepository = attributeOfCalculationObjectRepository;
    }

    @Override
    public List<AttributeOfCalculationObjectDto> getAll() {
        return attributeOfCalculationObjectRepository.getAll();
    }

    @Override
    public AttributeOfCalculationObjectDto getById(Long id) {
        return attributeOfCalculationObjectRepository.getById(id);
    }

    @Override
    public void create(AttributeOfCalculationObjectDto attribute) {
        attributeOfCalculationObjectRepository.save(
                new AttributeOfCalculationObject(
                        attribute.getName(),
                        attribute.getSortNumber(),
                        attribute.getIsService()
                )
        );
    }

    @Override
    public void update(AttributeOfCalculationObjectDto attribute) {
        attributeOfCalculationObjectRepository.save(
                new AttributeOfCalculationObject(
                        attribute.getId(),
                        attribute.getName(),
                        attribute.getSortNumber(),
                        attribute.getIsService()
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        attributeOfCalculationObjectRepository.deleteById(id);
    }
}
