package com.trade_accounting.services.impl;

import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeOfContractorServiceImpl implements TypeOfContractorService {

    private final TypeOfContractorRepository typeOfContractorRepository;

    public TypeOfContractorServiceImpl(TypeOfContractorRepository typeOfContractorRepository) {
        this.typeOfContractorRepository = typeOfContractorRepository;
    }

    @Override
    public List<TypeOfContractorDto> getAll() {
        return typeOfContractorRepository.getAll();
    }

    @Override
    public TypeOfContractorDto getById(Long id) {
        return typeOfContractorRepository.getById(id);
    }

    @Override
    public void create(TypeOfContractorDto typeOfContractorDto) {

        typeOfContractorRepository.save(
                new TypeOfContractor(
                        typeOfContractorDto.getName(),
                        typeOfContractorDto.getSortNumber()
                )
        );
    }

    @Override
    public void update(TypeOfContractorDto typeOfContractorDto) {
        typeOfContractorRepository.save(
                new TypeOfContractor(
                        typeOfContractorDto.getId(),
                        typeOfContractorDto.getName(),
                        typeOfContractorDto.getSortNumber()
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        typeOfContractorRepository.deleteById(id);
    }

    @Override
    public TypeOfContractor getByName(String name){
        return typeOfContractorRepository.getByName(name);
    }
}
