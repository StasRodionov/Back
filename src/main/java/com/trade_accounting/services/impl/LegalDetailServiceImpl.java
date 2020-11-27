package com.trade_accounting.services.impl;

import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.LegalDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LegalDetailServiceImpl implements LegalDetailService {

    private final LegalDetailRepository legalDetailRepository;

    private final TypeOfContractorRepository typeOfContractorRepository;

    public LegalDetailServiceImpl(LegalDetailRepository legalDetailRepository, TypeOfContractorRepository typeOfContractorRepository) {
        this.legalDetailRepository = legalDetailRepository;
        this.typeOfContractorRepository = typeOfContractorRepository;
    }

    @Override
    public List<LegalDetailDto> getAll() {
        List<LegalDetailDto> listLegalDetailDto = legalDetailRepository.getAll();
        for(LegalDetailDto legalDetailDto : listLegalDetailDto) {
            legalDetailDto.setTypeOfContractorDto(
                    typeOfContractorRepository
                            .getById(legalDetailDto.getTypeOfContractorDto().getId()));
        }
        return listLegalDetailDto;
    }

    @Override
    public LegalDetailDto getById(Long id) {
        LegalDetailDto legalDetailDto = legalDetailRepository.getById(id);
        legalDetailDto.setTypeOfContractorDto(
                typeOfContractorRepository
                        .getById(legalDetailDto.getTypeOfContractorDto().getId()));
        return legalDetailDto;
    }

    @Override
    public void create(LegalDetailDto legalDetailDto) {
        legalDetailRepository.save(
                new LegalDetail(
                        legalDetailDto.getLastName(),
                        legalDetailDto.getFirstName(),
                        legalDetailDto.getMiddleName(),
                        legalDetailDto.getAddress(),
                        legalDetailDto.getCommentToAddress(),
                        legalDetailDto.getInn(),
                        legalDetailDto.getOkpo(),
                        legalDetailDto.getOgrnip(),
                        legalDetailDto.getNumberOfTheCertificate(),
                        legalDetailDto.getDateOfTheCertificate(),
                        new TypeOfContractor(
                                legalDetailDto.getTypeOfContractorDto().getName(),
                                legalDetailDto.getTypeOfContractorDto().getSortNumber()
                        )
                )
        );
    }

    @Override
    public void update(LegalDetailDto legalDetailDto) {
        legalDetailRepository.save(
                new LegalDetail(
                        legalDetailDto.getId(),
                        legalDetailDto.getLastName(),
                        legalDetailDto.getFirstName(),
                        legalDetailDto.getMiddleName(),
                        legalDetailDto.getAddress(),
                        legalDetailDto.getCommentToAddress(),
                        legalDetailDto.getInn(),
                        legalDetailDto.getOkpo(),
                        legalDetailDto.getOgrnip(),
                        legalDetailDto.getNumberOfTheCertificate(),
                        legalDetailDto.getDateOfTheCertificate(),
                        new TypeOfContractor(
                                legalDetailDto.getTypeOfContractorDto().getId(),
                                legalDetailDto.getTypeOfContractorDto().getName(),
                                legalDetailDto.getTypeOfContractorDto().getSortNumber()
                        )
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        legalDetailRepository.deleteById(id);
    }
}
