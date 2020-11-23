package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeOfContractorServiceImpl implements TypeOfContractorService {

    private final TypeOfContractorRepository typeOfContractorRepository;

    public TypeOfContractorServiceImpl(TypeOfContractorRepository typeOfContractorRepository) {
        this.typeOfContractorRepository = typeOfContractorRepository;
    }
}
