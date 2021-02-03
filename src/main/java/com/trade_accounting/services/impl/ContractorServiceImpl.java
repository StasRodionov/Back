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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<ContractorDto> contractorDtos = contractorRepository.getAll();
        for (ContractorDto contractorDto : contractorDtos) {

            contractorDto.setContractorGroupDto(contractorGroupRepository.getContractorGroupByContractorId(contractorDto.getId()));
            contractorDto.setTypeOfContractorDto(typeOfContractorRepository.getTypeOfContractorByContractorId(contractorDto.getId()));
            contractorDto.setTypeOfPriceDto(typeOfPriceRepository.getTypeOfPriceByContractorId(contractorDto.getId()));
            contractorDto.setLegalDetailDto(legalDetailRepository.getLegalDetailByContractorId(contractorDto.getId()));

            List<BankAccount> bankAccountList = bankAccountRepository.getBankAccountByContractorId(contractorDto.getId());
            contractorDto.setBankAccountDto(bankAccountList.stream().map(bankAccount -> bankAccountRepository.getById(bankAccount.getId())).collect(Collectors.toList()));
        }

        return contractorDtos;
    }

    public List<ContractorDto> getAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contractorRepository.getAll();
        } else {
            return contractorRepository.search(stringFilter);
        }
    }

    @Override
    public ContractorDto getById(Long id) {

        ContractorDto contractorDto = contractorRepository.getById(id);

        contractorDto.setContractorGroupDto(contractorGroupRepository.getContractorGroupByContractorId(id));
        contractorDto.setTypeOfContractorDto(typeOfContractorRepository.getTypeOfContractorByContractorId(id));
        contractorDto.setTypeOfPriceDto(typeOfPriceRepository.getTypeOfPriceByContractorId(id));
        contractorDto.setLegalDetailDto(legalDetailRepository.getLegalDetailByContractorId(id));

        List<BankAccount> bankAccountList = bankAccountRepository.getBankAccountByContractorId(id);
        contractorDto.setBankAccountDto(bankAccountList.stream().map(bankAccount -> bankAccountRepository.getById(bankAccount.getId())).collect(Collectors.toList()));

        return contractorDto;
    }

    @Override
    public void create(ContractorDto contractorDto) {

        List<BankAccount> bankAccounts = new ArrayList<>();
        if (contractorDto.getBankAccountDto() != null) {
            for (BankAccountDto bankAccountDto : contractorDto.getBankAccountDto()) {
                bankAccounts.add(bankAccountRepository.getOne(bankAccountDto.getId()));
            }
        }

        contractorRepository.save(new Contractor(
                contractorDto.getName(),
                contractorDto.getInn(),
                contractorDto.getSortNumber(),
                contractorDto.getPhone(),
                contractorDto.getFax(),
                contractorDto.getEmail(),
                contractorDto.getAddress(),
                contractorDto.getCommentToAddress(),
                contractorDto.getComment(),

                contractorDto.getContractorGroupDto() != null
                        ? contractorGroupRepository.getOne(contractorDto.getContractorGroupDto().getId())
                        : null,
                contractorDto.getTypeOfContractorDto() != null
                        ? typeOfContractorRepository.getOne(contractorDto.getTypeOfContractorDto().getId())
                        : null,
                contractorDto.getTypeOfPriceDto() != null
                        ? typeOfPriceRepository.getOne(contractorDto.getTypeOfPriceDto().getId())
                        : null,
                bankAccounts,
                contractorDto.getLegalDetailDto() != null
                        ? legalDetailRepository.getOne(contractorDto.getLegalDetailDto().getId())
                        : null

        ));
    }

    @Override
    public void update(ContractorDto contractorDto) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        if (contractorDto.getBankAccountDto() != null) {
            for (BankAccountDto bankAccountDto : contractorDto.getBankAccountDto()) {
                bankAccounts.add(bankAccountRepository.getOne(bankAccountDto.getId()));
            }
        }

        contractorRepository.save(new Contractor(
                contractorDto.getId(),
                contractorDto.getName(),
                contractorDto.getInn(),
                contractorDto.getSortNumber(),
                contractorDto.getPhone(),
                contractorDto.getFax(),
                contractorDto.getEmail(),
                contractorDto.getAddress(),
                contractorDto.getCommentToAddress(),
                contractorDto.getComment(),

                contractorDto.getContractorGroupDto() != null
                        ? contractorGroupRepository.getOne(contractorDto.getContractorGroupDto().getId())
                        : null,
                contractorDto.getTypeOfContractorDto() != null
                        ? typeOfContractorRepository.getOne(contractorDto.getTypeOfContractorDto().getId())
                        : null,
                contractorDto.getTypeOfPriceDto() != null
                        ? typeOfPriceRepository.getOne(contractorDto.getTypeOfPriceDto().getId())
                        : null,
                bankAccounts,
                contractorDto.getLegalDetailDto() != null
                        ? legalDetailRepository.getOne(contractorDto.getLegalDetailDto().getId())
                        : null

        ));
    }

    @Override
    public void deleteById(Long id) {
        contractorRepository.deleteById(id);

    }

//    @PostConstruct
//    public void test() {
//        create(new ContractorDto(null, "Name", "1234567891", "11111", "10-11-12", "156161235", "165651651625@list.ru", "Street", "sdfsfd", "hi"));
//        List<ContractorDto> contractorDtos = getAll();
//        getById(1L);
//        System.out.println(contractorDtos.toString());
//        deleteById(1L);
//    }

}
