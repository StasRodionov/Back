package com.trade_accounting.services.impl;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.ContractorService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.ModelDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ContractorServiceImpl implements ContractorService {

    private final ContractorRepository contractorRepository;
    private final ContractorGroupRepository contractorGroupRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final TypeOfPriceRepository typeOfPriceRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final AddressRepository addressRepository;
    private final DtoMapper dtoMapper;

    public ContractorServiceImpl(ContractorRepository contractorRepository,
                                 ContractorGroupRepository contractorGroupRepository,
                                 TypeOfContractorRepository typeOfContractorRepository,
                                 TypeOfPriceRepository typeOfPriceRepository,
                                 LegalDetailRepository legalDetailRepository, AddressRepository addressRepository, DtoMapper dtoMapper) {
        this.contractorRepository = contractorRepository;
        this.contractorGroupRepository = contractorGroupRepository;
        this.typeOfContractorRepository = typeOfContractorRepository;
        this.typeOfPriceRepository = typeOfPriceRepository;
        this.legalDetailRepository = legalDetailRepository;
        this.addressRepository = addressRepository;
        this.dtoMapper = dtoMapper;

    }

    @Override
    public List<ContractorDto> searchContractor(Specification<Contractor> specification) {
        return contractorRepository.findAll(specification).stream()
                .map(ModelDtoConverter::convertToContractorDto).collect(Collectors.toList());
    }

    @Override
    public List<ContractorDto> getAll() {
        log.info("запрошен список getAll ");
        List<Contractor> list = contractorRepository.findAll();
        return list.stream()
                .map(dtoMapper::contractorToContractorDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<ContractorDto> getAll(String searchTerm) {
        if (searchTerm.equals("null") || searchTerm.isEmpty()) {
            log.info("запрошен список Query getAll через getAll(String searchTerm) ");
            List<Contractor> all = contractorRepository.findAll();
            return all.stream().map(dtoMapper::contractorToContractorDto).collect(Collectors.toList());
        } else {
            List<Contractor> list = contractorRepository.search(searchTerm);
            return list.stream().map(dtoMapper::contractorToContractorDto).collect(Collectors.toList());
        }
    }

    @Override
    public ContractorDto getById(Long id) {
        return dtoMapper.contractorToContractorDto(
                contractorRepository.findById(id).orElse(new Contractor())
        );
    }

    @Override
    public ContractorDto create(ContractorDto contractorDto) {
        log.info("добавление нового контрагента ");
        Contractor contractor = dtoMapper.contractorDtoToContractor(contractorDto);
        contractor.setContractorGroup(
                dtoMapper.contractorGroupDtoToContractorGroup(
                        contractorDto.getContractorGroupDto()
                )
        );

        contractor.setTypeOfContractor(
                dtoMapper.typeOfContractorDtoToTypeOfContractor(
                        contractorDto.getTypeOfContractorDto()
                )
        );

        contractor.setTypeOfPrice(
                dtoMapper.typeOfPriceDtoToTypeOfPrice(
                        contractorDto.getTypeOfPriceDto()
                )
        );

        contractor.setLegalDetail(
                legalDetailRepository.save(
                        dtoMapper.legalDetailDtoToLegalDetail(
                                contractorDto.getLegalDetailDto()
                        )
                )
        );
        contractorRepository.save(contractor);
        return contractorDto;
    }

    @Override
    public ContractorDto update(ContractorDto contractorDto) {
        log.info("обновление контрагента ");
        Contractor contractor = dtoMapper.contractorDtoToContractor(contractorDto);

        contractor.setContractorGroup(
                contractorGroupRepository.findById(contractorDto.getContractorGroupDto().getId()).orElse(null));

        contractor.setTypeOfContractor(
                typeOfContractorRepository.findById(contractorDto.getTypeOfContractorDto().getId()).orElse(null));

        contractor.setTypeOfPrice(
                typeOfPriceRepository.findById(contractorDto.getTypeOfPriceDto().getId()).orElse(null));

        contractor.setLegalDetail(
                legalDetailRepository.findById(contractorDto.getLegalDetailDto().getId()).orElse(null));

        contractorRepository.save(contractor);

        return contractorDto;
    }

    @Override
    public void deleteById(Long id) {
        log.info("удаление контрагента ");
        contractorRepository.deleteById(id);
    }
}
