package com.trade_accounting.services.impl;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.services.interfaces.ContractService;
import com.trade_accounting.utils.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;
    private final ContractMapper contractMapper;

    @Override
    public List<ContractDto> getAll() {
        return contractMapper.toContractDtoList(contractRepository.findAll());
    }

    @Override
    public List<ContractDto> getAll(String searchContr) {
        if ("null".equals(searchContr) || searchContr.isEmpty()) {
            List<Contract> all = contractRepository.findAll();
            return all.stream().map(contractMapper::contractToContractDto).collect(Collectors.toList());
        } else {
            List<Contract> list = contractRepository.search(searchContr);
            return list.stream().map(contractMapper::contractToContractDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<ContractDto> search(Specification<Contract> specification) {
        return executeSearch(contractRepository, contractMapper::contractToContractDto, specification);
    }

    @Override
    public ContractDto getById(Long id) {
        return contractMapper.contractToContractDto(contractRepository.getOne(id));
    }

    @Override
    public ContractDto create(ContractDto contractDto) {
        Contract contractSaved = contractRepository.save(contractMapper.contractDtoToContract(contractDto));
        contractDto.setId(contractSaved.getId());
        return contractDto;
    }

    @Override
    public ContractDto update(ContractDto contractDto) {
        contractRepository.save(contractMapper.contractDtoToContract(contractDto));
        return contractDto;
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteAllByContractId(id);
        contractRepository.deleteById(id);
    }


}
