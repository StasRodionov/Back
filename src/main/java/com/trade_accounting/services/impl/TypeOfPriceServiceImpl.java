package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        Optional<TypeOfPrice> typeOfPrice = typeOfPriceRepository.findById(id);
        if(typeOfPrice.isEmpty()){
            throw new NotFoundEntityException("There is not typeOfPrice with id " + id);
        }
        return dtoMapper.typeOfPriceToTypeOfPriceDto(typeOfPrice.get());

    }

    @Override
    public TypeOfPriceDto create(TypeOfPriceDto typeOfPriceDto) {
        TypeOfPrice savedTypeOfPrice = typeOfPriceRepository.save(
            dtoMapper.typeOfPriceDtoToTypeOfPrice(typeOfPriceDto)
        );

        return dtoMapper.typeOfPriceToTypeOfPriceDto(savedTypeOfPrice);
    }

    @Override
    public TypeOfPriceDto update(TypeOfPriceDto typeOfPriceDto) {
        return create(typeOfPriceDto);
    }

    @Override
    public void deleteById(Long id) {
        typeOfPriceRepository.deleteById(id);
    }
}
