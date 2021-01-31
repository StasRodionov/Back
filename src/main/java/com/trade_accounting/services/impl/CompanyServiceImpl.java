package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.repositories.CompanyRepository;
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

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              LegalDetailService legalDetailService) {
        this.companyRepository = companyRepository;
        this.legalDetailService = legalDetailService;
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyDto> companyDtos = companyRepository.getAll();
        for (CompanyDto companyDto : companyDtos) {
            companyDto.setLegalDetailDto(
                    legalDetailService.getById(companyDto.getLegalDetailDto().getId()));
        }
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
        companyRepository.save(convertToCompany(companyDto));
    }

    @Override
    public void update(CompanyDto companyDto) {
        companyRepository.save(convertToCompany(companyDto));
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public void create(Company company) {
        companyRepository.save(company);
    }

    private Company convertToCompany(CompanyDto dto) {
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
                new LegalDetail(
                        dto.getLegalDetailDto().getId(),
                        dto.getLegalDetailDto().getLastName(),
                        dto.getLegalDetailDto().getFirstName(),
                        dto.getLegalDetailDto().getMiddleName(),
                        dto.getLegalDetailDto().getAddress(),
                        dto.getLegalDetailDto().getCommentToAddress(),
                        dto.getLegalDetailDto().getInn(),
                        dto.getLegalDetailDto().getOkpo(),
                        dto.getLegalDetailDto().getOgrnip(),
                        dto.getLegalDetailDto().getNumberOfTheCertificate(),
                        LocalDate.parse(dto.getLegalDetailDto().getDateOfTheCertificate()),
                        new TypeOfContractor(
                                dto.getLegalDetailDto().getTypeOfContractorDto().getId(),
                                dto.getLegalDetailDto().getTypeOfContractorDto().getName(),
                                dto.getLegalDetailDto().getTypeOfContractorDto().getSortNumber())));
    }
}
