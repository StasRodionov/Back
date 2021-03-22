package com.trade_accounting.services.impl;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.services.interfaces.ContractService;
import com.trade_accounting.utils.DtoMapper;
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
//        return contractRepository.findAll().stream().map(dtoMapper::contractToContractDto).collect(Collectors.toList());
        return dtoMapper.toContractDtoList(contractRepository.findAll());
    }

    @Override
    public List<ContractDto> search(Specification<Contract> specification) {
        return contractRepository.findAll(specification).stream().map(dtoMapper::contractToContractDto).collect(Collectors.toList());
    }

    @Override
    public ContractDto getById(Long id) {
        return contractRepository.findById(id).map(dtoMapper::contractToContractDto).get();
    }

    @Override
    public void create(ContractDto contractDto) {
        contractRepository.save(dtoMapper.contractDtoToContract(contractDto));
    }

    @Override
    public void update(ContractDto contractDto) {
        contractRepository.save(dtoMapper.contractDtoToContract(contractDto));
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteAllByContractId(id);
        contractRepository.deleteById(id);
    }
}
