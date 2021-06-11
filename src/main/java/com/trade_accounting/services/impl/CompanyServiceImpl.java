package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        return companyRepository.getAll();
    }

    @Override
    public List<CompanyDto> search(Specification<Company> spec) {
        return executeSearch(companyRepository, dtoMapper::companyToCompanyDto, spec);
    }

    @Override
    public CompanyDto getById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return dtoMapper.companyToCompanyDto(companyOptional.orElse(new Company()));
    }

    @Override
    public CompanyDto getByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        Company company = dtoMapper.companyDtoToCompany(companyDto);

        company.setLegalDetail(
                dtoMapper.legalDetailDtoToLegalDetail(legalDetailRepository.getById(
                        companyDto.getLegalDetailDto().getId()))
        );

        company.setBankAccounts(
                companyDto.getBankAccountDto().stream()
                        .map(
                                bankAccount -> bankAccountRepository
                                        .save(dtoMapper.bankAccountDtoToBankAccount(bankAccount))
                        )
                        .collect(Collectors.toList())
        );
        Company companySaved = companyRepository.save(company);
        companyDto.setId(companySaved.getId());
        return companyDto;
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        Company company = dtoMapper.companyDtoToCompany(companyDto);

        company.setLegalDetail(
                legalDetailRepository.findById(
                        companyDto.getLegalDetailDto().getId()
                ).orElse(null)
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
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

}
