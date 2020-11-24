package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.services.interfaces.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.getOne(id);
    }

    @Override
    public Company getByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public void update() {
        // todo дописать когда будет готов DTO
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
