package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.services.interfaces.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final LegalDetailRepository legalDetailRepository;

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
    public CompanyDto getById(Long id) {
        CompanyDto companyDto = companyRepository.getById(id);
        companyDto.setLegalDetailDto(
                legalDetailRepository.getById(companyDto.getLegalDetailDto().getId()));
        return companyDto;
    }

    @Override
    public CompanyDto getByEmail(String email) {
        return companyRepository.findByEmail(email);
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
}
