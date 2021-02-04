package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.services.interfaces.CompanyService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper = new ModelMapper();

    public CompanyServiceImpl(CompanyRepository companyRepository, LegalDetailRepository legalDetailRepository) {
        this.companyRepository = companyRepository;
        this.legalDetailRepository = legalDetailRepository;
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyDto> companyDtos = companyRepository.getAll();
        for(CompanyDto companyDto : companyDtos) {
            companyDto.setLegalDetailDto(
                legalDetailRepository.getById(companyDto.getLegalDetailDto().getId())
            );
        }
        return companyDtos;
    }

    @Override
    public List<CompanyDto> search(Specification<Company> spec) {
        return companyRepository.findAll(spec).stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CompanyDto getById(Long id) {
        CompanyDto companyDto = companyRepository.getById(id);
        companyDto.setLegalDetailDto(
                legalDetailRepository.getById(companyDto.getLegalDetailDto().getId())
        );
        return companyDto;
    }

    @Override
    public CompanyDto getByEmail(String email) {

        CompanyDto companyDto = companyRepository.findByEmail(email);
        companyDto.setLegalDetailDto(
                legalDetailRepository.getById(companyDto.getLegalDetailDto().getId())
        );
        return companyDto;
    }

    @Override
    public void create(CompanyDto companyDto) {
        companyRepository.save(new Company(
                        companyDto.getName(),
                        companyDto.getInn(),
                        companyDto.getSortNumber(),
                        companyDto.getPhone(),
                        companyDto.getFax(),
                        companyDto.getEmail(),
                        companyDto.getPayerVat(),
                        companyDto.getAddress(),
                        companyDto.getCommentToAddress(),
                        companyDto.getLeader(),
                        companyDto.getLeaderManagerPosition(),
                        companyDto.getLeaderSignature(),
                        companyDto.getChiefAccountant(),
                        companyDto.getChiefAccountantSignature(),
                        companyDto.getStamp(),
                        legalDetailRepository.getOne(companyDto.getLegalDetailDto().getId())
                )
        );
    }

    @Override
    public void update(CompanyDto companyDto) {
        companyRepository.save(new Company(
                        companyDto.getId(),
                        companyDto.getName(),
                        companyDto.getInn(),
                        companyDto.getSortNumber(),
                        companyDto.getPhone(),
                        companyDto.getFax(),
                        companyDto.getEmail(),
                        companyDto.getPayerVat(),
                        companyDto.getAddress(),
                        companyDto.getCommentToAddress(),
                        companyDto.getLeader(),
                        companyDto.getLeaderManagerPosition(),
                        companyDto.getLeaderSignature(),
                        companyDto.getChiefAccountant(),
                        companyDto.getChiefAccountantSignature(),
                        companyDto.getStamp(),
                        legalDetailRepository.getOne(companyDto.getLegalDetailDto().getId())
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public void create(Company company) {
        companyRepository.save(company);
    }

    private CompanyDto convertToDto(Company company) {
        CompanyDto companyDto = modelMapper.map(company, CompanyDto.class);
        companyDto.setLegalDetailDto(legalDetailRepository.getById(company.getLegalDetail().getId()));

        return companyDto;
    }
}
