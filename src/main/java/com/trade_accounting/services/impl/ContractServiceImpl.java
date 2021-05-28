package com.trade_accounting.services.impl;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.services.interfaces.ContractService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.ModelDtoConverter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;
    private final DtoMapper dtoMapper;

    public ContractServiceImpl(ContractRepository contractRepository,
                               PaymentRepository paymentRepository,
                               DtoMapper dtoMapper) {
        this.contractRepository = contractRepository;
        this.paymentRepository = paymentRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ContractDto> getAll() {
        return dtoMapper.toContractDtoList(contractRepository.findAll());
    }

    @Override
    public List<ContractDto> getAll(String searchContr) {
        if("null".equals(searchTerm) || searchContr.isEmpty()) {
            List<Contract> all = contractRepository.findAll();
            return all.stream().map(dtoMapper::contractToContractDto).collect(Collectors.toList());
        } else {
            List<Contract> list = contractRepository.search(searchContr);
            return list.stream().map(dtoMapper::contractToContractDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<ContractDto> search(Specification<Contract> specification) {
        return executeSearch(contractRepository, ModelDtoConverter::convertToContactDto, specification);
    }

    @Override
    public ContractDto getById(Long id) {
        return dtoMapper.contractToContractDto(contractRepository.getOne(id));
    }

    @Override
    public ContractDto create(ContractDto contractDto) {
        Contract contractSaved = contractRepository.save(dtoMapper.contractDtoToContract(contractDto));
        contractDto.setId(contractSaved.getId());
        return contractDto;
    }

    @Override
    public ContractDto update(ContractDto contractDto) {
        contractRepository.save(dtoMapper.contractDtoToContract(contractDto));
        return contractDto;
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteAllByContractId(id);
        contractRepository.deleteById(id);
    }


}
