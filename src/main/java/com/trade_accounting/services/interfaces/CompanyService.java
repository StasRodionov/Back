package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> getAll();

    List<CompanyDto> search(Specification<Company> specification);

    CompanyDto getById(Long id);

    CompanyDto getByEmail(String email);

    void create(CompanyDto companyDto);

    void update(CompanyDto companyDto);

    void deleteById(Long id);

    void create(Company company);
}
