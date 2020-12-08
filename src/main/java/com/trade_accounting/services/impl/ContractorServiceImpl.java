package com.trade_accounting.services.impl;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.ContractorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContractorServiceImpl implements ContractorService {

    private final ContractorRepository contractorRepository;
    private final ContractorGroupRepository contractorGroupRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final TypeOfPriceRepository typeOfPriceRepository;
    private final BankAccountRepository bankAccountRepository;
    private final LegalDetailRepository legalDetailRepository;

    public ContractorServiceImpl(ContractorRepository contractorRepository,
                                 ContractorGroupRepository contractorGroupRepository,
                                 TypeOfContractorRepository typeOfContractorRepository,
                                 TypeOfPriceRepository typeOfPriceRepository,
                                 BankAccountRepository bankAccountRepository,
                                 LegalDetailRepository legalDetailRepository) {
        this.contractorRepository = contractorRepository;
        this.contractorGroupRepository = contractorGroupRepository;
        this.typeOfContractorRepository = typeOfContractorRepository;
        this.typeOfPriceRepository = typeOfPriceRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.legalDetailRepository = legalDetailRepository;
    }

    @Override
    public List<ContractorDto> getAll() {
        return contractorRepository.getAll();
    }

    @Override
    public ContractorDto getById(Long id) {
        return contractorRepository.getById(id);
    }

    @Override
    public void create(ContractorDto contractorDto) {

        List<BankAccount> bankAccounts = new ArrayList<>();
        for (BankAccountDto bankAccountDto : contractorDto.getBankAccountDto()) {
            bankAccounts.add(bankAccountRepository.getOne(bankAccountDto.getId()));
        }

        contractorRepository.save(new Contractor(
                contractorDto.getName(),
                contractorDto.getInn(),
                contractorDto.getSortNumber(),
                contractorDto.getPhone(),
                contractorDto.getFax(),
                contractorDto.getEmail(),
                contractorDto.getCommentToAddress(),
                contractorDto.getAddress(),
                contractorDto.getComment(),
                contractorGroupRepository.getOne(contractorDto.getContractorGroupDto().getId()),
                typeOfContractorRepository.getOne(contractorDto.getTypeOfContractorDto().getId()),
                typeOfPriceRepository.getOne(contractorDto.getTypeOfPriceDto().getId()),
                bankAccounts,
                legalDetailRepository.getOne(contractorDto.getLegalDetailDto().getId())

        ));
    }

    @Override
    public void update(ContractorDto contractorDto) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        for (BankAccountDto bankAccountDto : contractorDto.getBankAccountDto()) {
            bankAccounts.add(bankAccountRepository.getOne(bankAccountDto.getId()));
        }

        contractorRepository.save(new Contractor(
                contractorDto.getId(),
                contractorDto.getName(),
                contractorDto.getInn(),
                contractorDto.getSortNumber(),
                contractorDto.getPhone(),
                contractorDto.getFax(),
                contractorDto.getEmail(),
                contractorDto.getCommentToAddress(),
                contractorDto.getAddress(),
                contractorDto.getComment(),
                contractorGroupRepository.getOne(contractorDto.getContractorGroupDto().getId()),
                typeOfContractorRepository.getOne(contractorDto.getTypeOfContractorDto().getId()),
                typeOfPriceRepository.getOne(contractorDto.getTypeOfPriceDto().getId()),
                bankAccounts,
                legalDetailRepository.getOne(contractorDto.getLegalDetailDto().getId())

        ));
    }

    @Override
    public void deleteById(Long id) {
        contractorRepository.deleteById(id);

    }
}
