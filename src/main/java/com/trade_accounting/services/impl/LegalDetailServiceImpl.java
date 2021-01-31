package com.trade_accounting.services.impl;

import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.services.interfaces.LegalDetailService;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LegalDetailServiceImpl implements LegalDetailService {

    private final LegalDetailRepository legalDetailRepository;
    private final TypeOfContractorService typeOfContractorService;

    public LegalDetailServiceImpl(LegalDetailRepository legalDetailRepository,
                                  TypeOfContractorService typeOfContractorService) {
        this.legalDetailRepository = legalDetailRepository;
        this.typeOfContractorService = typeOfContractorService;
    }

    @Override
    public List<LegalDetailDto> getAll() {
        List<LegalDetailDto> listLegalDetailDto = legalDetailRepository.getAll();
        for(LegalDetailDto legalDetailDto : listLegalDetailDto) {
            legalDetailDto.setTypeOfContractorDto(
                    typeOfContractorService.getById(legalDetailDto.getTypeOfContractorDto().getId()));
        }
        return listLegalDetailDto;
    }

    @Override
    public LegalDetailDto getById(Long id) {
        LegalDetailDto legalDetailDto = legalDetailRepository.getById(id);
        legalDetailDto.setTypeOfContractorDto(
                typeOfContractorService.getById(legalDetailDto.getTypeOfContractorDto().getId()));
        return legalDetailDto;
    }

    @Override
    public void create(LegalDetailDto legalDetailDto) {
        update(legalDetailDto);
    }

    @Override
    public void update(LegalDetailDto legalDetailDto) {
        legalDetailRepository.save(convertToLegalDetail(legalDetailDto));
    }

    @Override
    public void deleteById(Long id) {
        legalDetailRepository.deleteById(id);
    }

    @Override
    public void create(LegalDetail legalDetail) {
        legalDetailRepository.save(legalDetail);
    }

    private LegalDetail convertToLegalDetail(LegalDetailDto dto) {
        return new LegalDetail(
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
                new TypeOfContractor(
                        dto.getTypeOfContractorDto().getId(),
                        dto.getTypeOfContractorDto().getName(),
                        dto.getTypeOfContractorDto().getSortNumber()));
    }
}
