package com.trade_accounting.services.impl;

import com.trade_accounting.models.Address;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.AddressDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.utils.DtoMapper;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final BankAccountRepository bankAccountRepository;
    private final AddressRepository addressRepository;

    private final DtoMapper dtoMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              LegalDetailRepository legalDetailRepository,
                              BankAccountRepository bankAccountRepository,
                              AddressRepository addressRepository,
                              DtoMapper dtoMapper) {
        this.companyRepository = companyRepository;
        this.legalDetailRepository = legalDetailRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.addressRepository = addressRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<CompanyDto> getAll() {
        List<Company> companys = companyRepository.findAll();
        List<CompanyDto> companyDtos = new ArrayList<>();
        for (Company company:companys) {
            companyDtos.add(dtoMapper.companyToCompanyDto(company));
        }
        return companyDtos;
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
        return companyRepository.findCompanyByEmail(email);
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        System.out.println(companyDto);
        Company company = dtoMapper.companyDtoToCompany(companyDto);
        company.setAddress(addressRepository.getOne(companyDto.getAddressId()));

        company.setLegalDetail(
                legalDetailRepository.getOne(companyDto.getLegalDetailDtoId())
        );

        company.setBankAccounts(
                companyDto.getBankAccountDtoIds().stream()
                        .map(
                                bankAccountId -> bankAccountRepository
                                        .getOne(bankAccountId)
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
        company.setAddress(addressRepository.getOne(companyDto.getAddressId()));
        company.setLegalDetail(
                legalDetailRepository.getOne(companyDto.getLegalDetailDtoId())
        );

        company.setBankAccounts(
                companyDto.getBankAccountDtoIds().stream()
                        .map(
                                bankAccountId -> bankAccountRepository
                                        .getOne(bankAccountId)
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
