package com.trade_accounting.services.impl;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.LegalDetailService;
import com.trade_accounting.utils.ModelDtoConverter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final LegalDetailService legalDetailService;
    private final LegalDetailRepository legalDetailRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final BankAccountRepository bankAccountRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              LegalDetailService legalDetailService,
                              LegalDetailRepository legalDetailRepository,
                              TypeOfContractorRepository typeOfContractorRepository, BankAccountRepository bankAccountRepository) {
        this.companyRepository = companyRepository;
        this.legalDetailService = legalDetailService;
        this.legalDetailRepository = legalDetailRepository;
        this.typeOfContractorRepository = typeOfContractorRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyDto> companyDtos = companyRepository.getAll();

        for (CompanyDto companyDto : companyDtos) {
            companyDto.setLegalDetailDto(
                    legalDetailService.getById(companyDto.getLegalDetailDto().getId()));
            List<BankAccount> bankAccounts = bankAccountRepository.getBankAccountByCompanyId(companyDto.getId());
            companyDto.setBankAccountDto(bankAccounts.stream().map(bankAccount -> bankAccountRepository.getById(bankAccount.getId())).collect(Collectors.toList()));
        }
        companyDtos.sort(Comparator.comparing(CompanyDto::getSortNumber));
        return companyDtos;
    }

    @Override
    public List<CompanyDto> search(Specification<Company> spec) {
        return companyRepository.findAll(spec).stream()
                .map(ModelDtoConverter::convertToCompanyDto).collect(Collectors.toList());
    }

    @Override
    public CompanyDto getById(Long id) {
        CompanyDto companyDto = companyRepository.getById(id);
        companyDto.setLegalDetailDto(
                legalDetailService.getById(companyDto.getLegalDetailDto().getId()));
        List<BankAccount> bankAccounts = bankAccountRepository.getBankAccountByCompanyId(companyDto.getId());
        companyDto.setBankAccountDto(bankAccounts.stream().map(bankAccount -> bankAccountRepository.getById(bankAccount.getId())).collect(Collectors.toList()));
        return companyDto;
    }

    @Override
    public CompanyDto getByEmail(String email) {
        CompanyDto companyDto = companyRepository.findByEmail(email);
        companyDto.setLegalDetailDto(
                legalDetailService.getById(companyDto.getLegalDetailDto().getId()));
        List<BankAccount> bankAccounts = bankAccountRepository.getBankAccountByCompanyId(companyDto.getId());
        companyDto.setBankAccountDto(bankAccounts.stream().map(bankAccount -> bankAccountRepository.getById(bankAccount.getId())).collect(Collectors.toList()));
        return companyDto;
    }

    @Override
    public void create(CompanyDto companyDto) {
        update(companyDto);
    }

    @Override
    public void update(CompanyDto companyDto) {
        companyRepository.save(ModelDtoConverter.convertToCompany(companyDto,
                legalDetailRepository.save(ModelDtoConverter.convertToLegalDetail(companyDto.getLegalDetailDto(),
                        typeOfContractorRepository.save(ModelDtoConverter.convertToTypeOfContractor(
                                companyDto.getLegalDetailDto().getTypeOfContractorDto())))),
                bankAccountRepository.saveAll(ModelDtoConverter.convertToListOfBankAccount(companyDto.getBankAccountDto()))));
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
