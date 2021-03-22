package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.TypeOfContractorDto;

import java.util.List;

public interface TypeOfContractorService {

    List<TypeOfContractorDto> getAll();

    TypeOfContractorDto getById(Long id);

    TypeOfContractorDto create(TypeOfContractorDto typeOfContractorDto);

    TypeOfContractorDto update(TypeOfContractorDto typeOfContractorDto);

    void deleteById(Long id);

    TypeOfContractorDto getByName(String name);
}
