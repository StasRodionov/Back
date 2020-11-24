package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.TaxSystemRepository;
import com.trade_accounting.services.interfaces.TaxSystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TaxSystemServiceImpl implements TaxSystemService {

    private final TaxSystemRepository taxSystemRepository;

    public TaxSystemServiceImpl(TaxSystemRepository taxSystemRepository) {
        this.taxSystemRepository = taxSystemRepository;
    }
}
