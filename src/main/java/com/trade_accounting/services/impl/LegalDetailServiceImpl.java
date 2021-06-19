package com.trade_accounting.services.impl;

import com.trade_accounting.models.Address;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.LegalDetailService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LegalDetailServiceImpl implements LegalDetailService {

    private final LegalDetailRepository legalDetailRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final AddressRepository addressRepository;
    private final DtoMapper dtoMapper;

    public LegalDetailServiceImpl(LegalDetailRepository legalDetailRepository,
                                  TypeOfContractorRepository typeOfContractorRepository, AddressRepository addressRepository, DtoMapper dtoMapper) {
        this.legalDetailRepository = legalDetailRepository;
        this.typeOfContractorRepository = typeOfContractorRepository;
        this.addressRepository = addressRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<LegalDetailDto> getAll() {
        return legalDetailRepository.findAll().stream()
                .map(dtoMapper::legalDetailToLegalDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public LegalDetailDto getById(Long id) {
        return dtoMapper.legalDetailToLegalDetailDto(
                legalDetailRepository.findById(id).orElse(new LegalDetail())
        );
    }

    @Override
    public LegalDetailDto create(LegalDetailDto legalDetailDto) {
        LegalDetail legalDetail = dtoMapper.legalDetailDtoToLegalDetail(legalDetailDto);

        legalDetail.setAddress(addressRepository.getOne(
                legalDetailDto.getAddressDtoId()
        ));

        legalDetail.setTypeOfContractor(
                typeOfContractorRepository.findById(
                        legalDetailDto.getTypeOfContractorDto().getId()
                ).orElse(null)
        );

        return dtoMapper.legalDetailToLegalDetailDto(
                legalDetailRepository.save(legalDetail)
        );
    }


    @Override
    public LegalDetailDto update(LegalDetailDto legalDetailDto) {
        LegalDetail legalDetail = dtoMapper.legalDetailDtoToLegalDetail(legalDetailDto);

        legalDetail.setAddress(addressRepository.getOne(legalDetailDto.getAddressDtoId()));

        legalDetail.setTypeOfContractor(
                typeOfContractorRepository.findById(
                        legalDetailDto.getTypeOfContractorDto().getId()
                ).orElse(null)
        );

        return dtoMapper.legalDetailToLegalDetailDto(
                legalDetailRepository.save(legalDetail)
        );
    }

    @Override
    public void deleteById(Long id) {
        legalDetailRepository.deleteById(id);
    }

}
