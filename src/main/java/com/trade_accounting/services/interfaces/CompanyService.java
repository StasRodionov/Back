package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CompanyService extends AbstractService<CompanyDto>   {

    List<CompanyDto> search(Specification<Company> specification);

    CompanyDto getByEmail(String email);
}
