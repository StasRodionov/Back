package com.trade_accounting.services.impl;

import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeOfPriceServiceImpl implements TypeOfPriceService {

    private final TypeOfPriceRepository typeOfPriceRepository;

    private final DtoMapper dtoMapper;

    public TypeOfPriceServiceImpl(TypeOfPriceRepository typeOfPriceRepository, DtoMapper dtoMapper) {
        this.typeOfPriceRepository = typeOfPriceRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<TypeOfPriceDto> getAll() {
        return typeOfPriceRepository.findAll().stream()
                .map(dtoMapper::typeOfPriceToTypeOfPriceDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeOfPriceDto getById(Long id) {
        return typeOfPriceRepository.getById(id);
    }

    @Override
    public void create(TypeOfPriceDto typeOfPriceDto) {
        typeOfPriceRepository.save(
                new TypeOfPrice(
                        typeOfPriceDto.getName(),
                        typeOfPriceDto.getSortNumber()
                )
        );
    }

    @Override
    public void update(TypeOfPriceDto typeOfPriceDto) {
        typeOfPriceRepository.save(
                new TypeOfPrice(
                        typeOfPriceDto.getId(),
                        typeOfPriceDto.getName(),
                        typeOfPriceDto.getSortNumber()
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        typeOfPriceRepository.deleteById(id);
    }
}
