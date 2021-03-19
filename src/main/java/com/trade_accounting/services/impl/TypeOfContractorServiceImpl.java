package com.trade_accounting.services.impl;

import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeOfContractorServiceImpl implements TypeOfContractorService {

    private final TypeOfContractorRepository typeOfContractorRepository;
    private final DtoMapper dtoMapper;

    public TypeOfContractorServiceImpl(TypeOfContractorRepository typeOfContractorRepository, DtoMapper dtoMapper) {
        this.typeOfContractorRepository = typeOfContractorRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<TypeOfContractorDto> getAll() {
        return typeOfContractorRepository.findAll().stream()
                .map(dtoMapper::typeOfContractorToTypeOfContractorDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeOfContractorDto getById(Long id) {
        Optional<TypeOfContractor> typeOfContractor = typeOfContractorRepository.findById(id);
        return dtoMapper.typeOfContractorToTypeOfContractorDto(typeOfContractor.orElse(new TypeOfContractor()));
    }

    @Override
    public TypeOfContractorDto create(TypeOfContractorDto typeOfContractorDto) {
        TypeOfContractor typeOfContractor = dtoMapper.typeOfContractorDtoToTypeOfContractor(typeOfContractorDto);
        return dtoMapper.typeOfContractorToTypeOfContractorDto(
                typeOfContractorRepository.save(typeOfContractor));
    }

    @Override
    public TypeOfContractorDto update(TypeOfContractorDto typeOfContractorDto) {
        return create(typeOfContractorDto);
    }

    @Override
    public void deleteById(Long id) {
        typeOfContractorRepository.deleteById(id);
    }

    @Override
    public TypeOfContractorDto getByName(String name){
        Optional<TypeOfContractor> typeOfContractor = typeOfContractorRepository.findByName(name);
        return dtoMapper.typeOfContractorToTypeOfContractorDto(typeOfContractor.orElse(new TypeOfContractor()));
    }
}
