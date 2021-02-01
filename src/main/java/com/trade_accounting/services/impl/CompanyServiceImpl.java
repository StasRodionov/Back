package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.LegalDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final LegalDetailService legalDetailService;
    private final LegalDetailRepository legalDetailRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              LegalDetailService legalDetailService,
                              LegalDetailRepository legalDetailRepository,
                              TypeOfContractorRepository typeOfContractorRepository) {
        this.companyRepository = companyRepository;
        this.legalDetailService = legalDetailService;
        this.legalDetailRepository = legalDetailRepository;
        this.typeOfContractorRepository = typeOfContractorRepository;
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyDto> companyDtos = companyRepository.getAll();

        for (CompanyDto companyDto : companyDtos) {
            companyDto.setLegalDetailDto(
                    legalDetailService.getById(companyDto.getLegalDetailDto().getId()));
        }
        companyDtos.sort((c1, c2) -> {
            long result = c1.getId() - c2.getId();
            return (int) (result / Math.abs(result));
        });
        return companyDtos;
    }

    @Override
    public CompanyDto getById(Long id) {
        CompanyDto companyDto = companyRepository.getById(id);
        companyDto.setLegalDetailDto(
                legalDetailService.getById(companyDto.getLegalDetailDto().getId()));
        return companyDto;
    }

    @Override
    public CompanyDto getByEmail(String email) {
        CompanyDto companyDto = companyRepository.findByEmail(email);
        companyDto.setLegalDetailDto(
                legalDetailService.getById(companyDto.getLegalDetailDto().getId()));
        return companyDto;
    }

    @Override
    public void create(CompanyDto companyDto) {
        update(companyDto);
    }

    @Override
    public void update(CompanyDto companyDto) {
        companyRepository.save(convertToCompany(companyDto,
                legalDetailRepository.save(convertToLegalDetail(companyDto.getLegalDetailDto(),
                        typeOfContractorRepository.save(convertToTypeOfContractor(
                                companyDto.getLegalDetailDto().getTypeOfContractorDto()))))));
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public void create(Company company) {
        companyRepository.save(company);
    }

    private Company convertToCompany(CompanyDto dto, LegalDetail legalDetail) {
        return new Company(
                dto.getId(),
                dto.getName(),
                dto.getInn(),
                dto.getSortNumber(),
                dto.getPhone(),
                dto.getFax(),
                dto.getEmail(),
                dto.getPayerVat(),
                dto.getAddress(),
                dto.getCommentToAddress(),
                dto.getLeader(),
                dto.getLeaderManagerPosition(),
                dto.getLeaderSignature(),
                dto.getChiefAccountant(),
                dto.getChiefAccountantSignature(),
                dto.getStamp(),
                legalDetail);
    }

    private LegalDetail convertToLegalDetail(LegalDetailDto dto, TypeOfContractor typeOfContractor) {
        return new LegalDetail(
                dto.getId(),
                dto.getLastName(),
                dto.getFirstName(),
                dto.getMiddleName(),
                dto.getAddress(),
                dto.getCommentToAddress(),
                dto.getInn(),
                dto.getOkpo(),
                dto.getOgrnip(),
                dto.getNumberOfTheCertificate(),
                LocalDate.parse(dto.getDateOfTheCertificate()),
                typeOfContractor);
    }

    private TypeOfContractor convertToTypeOfContractor(TypeOfContractorDto dto) {
        return new TypeOfContractor(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber());
    }
}
