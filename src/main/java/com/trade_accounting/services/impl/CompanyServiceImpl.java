package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.LegalDetailService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final BankAccountRepository bankAccountRepository;

    private final DtoMapper dtoMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              LegalDetailRepository legalDetailRepository,
                              BankAccountRepository bankAccountRepository,
                              DtoMapper dtoMapper) {
        this.companyRepository = companyRepository;
        this.legalDetailRepository = legalDetailRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<CompanyDto> getAll() {
        return companyRepository.findAll().stream()
                .map(dtoMapper::companyToCompanyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyDto> search(Specification<Company> spec) {
        return companyRepository.findAll(spec).stream()
                .map(dtoMapper::companyToCompanyDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto getById(Long id) {
        return dtoMapper.companyToCompanyDto(
                companyRepository.findById(id).orElse(new Company())
        );
    }

    @Override
    public CompanyDto getByEmail(String email) {
        return dtoMapper.companyToCompanyDto(
                companyRepository.findByEmail(email).orElse(new Company())
        );
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        Company company = dtoMapper.companyDtoToCompany(companyDto);

        company.setLegalDetail(
                legalDetailRepository.findById(companyDto.getLegalDetailDto().getId())
                        .orElse(null)
        );

        company.setBankAccounts(
                companyDto.getBankAccountDto().stream()
                .map(
                        bankAccount -> bankAccountRepository
                        .findById(bankAccount.getId())
                        .orElse(null)
                )
                .collect(Collectors.toList())
        );

        companyRepository.save(company);

        return companyDto;
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        return create(companyDto);
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public void create(Company company) {
        companyRepository.save(company);
    }
}
