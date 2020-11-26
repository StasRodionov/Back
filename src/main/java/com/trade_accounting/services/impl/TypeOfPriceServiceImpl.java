package com.trade_accounting.services.impl;

import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeOfPriceServiceImpl implements TypeOfPriceService {

    private final TypeOfPriceRepository typeOfPriceRepository;

    public TypeOfPriceServiceImpl(TypeOfPriceRepository typeOfPriceRepository) {
        this.typeOfPriceRepository = typeOfPriceRepository;
    }

    @Override
    public List<TypeOfPriceDto> getAll() {
        return typeOfPriceRepository.getAll();
    }

    @Override
    public TypeOfPriceDto getById(Long id) {
        return typeOfPriceRepository.getById(id);
    }

    @Override
    public void create(TypeOfPriceDto typeOfPriceDto) {
        TypeOfPrice type = new TypeOfPrice(typeOfPriceDto.getId(), typeOfPriceDto.getName(), typeOfPriceDto.getSortNumber());
        typeOfPriceRepository.save(type);
    }

    @Override
    public void update(TypeOfPriceDto typeOfPriceDto) {
        TypeOfPrice type = new TypeOfPrice(typeOfPriceDto.getId(), typeOfPriceDto.getName(), typeOfPriceDto.getSortNumber());
        typeOfPriceRepository.save(type);
    }

    @Override
    public void deleteById(Long id) {
        typeOfPriceRepository.deleteById(id);
    }
}
