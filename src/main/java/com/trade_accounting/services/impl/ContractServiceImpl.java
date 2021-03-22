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
    public List<ContractDto> search(Specification<Contract> specification) {
        return dtoMapper.toContractDtoList(contractRepository.findAll(specification));
    }

    @Override
    public ContractDto getById(Long id) {
        return dtoMapper.contractToContractDto(contractRepository.getOne(id));
    }

    @Override
    public void save(ContractDto contractDto) {
        contractRepository.save(dtoMapper.contractDtoToContract(contractDto));
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteAllByContractId(id);
        contractRepository.deleteById(id);
    }
}
