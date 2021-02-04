package com.trade_accounting.services.impl;

import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.LegalDetailService;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
import com.trade_accounting.utils.ModelDtoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LegalDetailServiceImpl implements LegalDetailService {

    private final LegalDetailRepository legalDetailRepository;
    private final TypeOfContractorService typeOfContractorService;
    private final TypeOfContractorRepository typeOfContractorRepository;

    public LegalDetailServiceImpl(LegalDetailRepository legalDetailRepository,
                                  TypeOfContractorService typeOfContractorService,
                                  TypeOfContractorRepository typeOfContractorRepository) {
        this.legalDetailRepository = legalDetailRepository;
        this.typeOfContractorService = typeOfContractorService;
        this.typeOfContractorRepository = typeOfContractorRepository;
    }

    @Override
    public List<LegalDetailDto> getAll() {
        List<LegalDetailDto> listLegalDetailDto = legalDetailRepository.getAll();
        for (LegalDetailDto legalDetailDto : listLegalDetailDto) {
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
        legalDetailRepository.save(ModelDtoConverter.convertToLegalDetail(legalDetailDto,
                typeOfContractorRepository.save(ModelDtoConverter.convertToTypeOfContractor(
                        legalDetailDto.getTypeOfContractorDto()))));
    }

    @Override
    public void deleteById(Long id) {
        legalDetailRepository.deleteById(id);
    }

    @Override
    public void create(LegalDetail legalDetail) {
        legalDetailRepository.save(legalDetail);
    }
}
