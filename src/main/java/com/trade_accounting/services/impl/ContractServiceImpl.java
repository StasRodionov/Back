package com.trade_accounting.services.impl;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.services.interfaces.ContractService;
import com.trade_accounting.utils.ModelDtoConverter;
import com.trade_accounting.utils.SortNumberConverter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final CompanyRepository companyRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ContractorRepository contractorRepository;
    private final LegalDetailRepository legalDetailRepository;

    public ContractServiceImpl(ContractRepository contractRepository,
                               CompanyRepository companyRepository,
                               BankAccountRepository bankAccountRepository,
                               ContractorRepository contractorRepository,
                               LegalDetailRepository legalDetailRepository) {
        this.contractRepository = contractRepository;
        this.companyRepository = companyRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.contractorRepository = contractorRepository;
        this.legalDetailRepository = legalDetailRepository;
    }

    @Override
    public List<ContractDto> getAll() {
        List<ContractDto> listContractDto = contractRepository.getAll();
        for (ContractDto contractDto : listContractDto) {
            contractDto.setCompanyDto(
                    companyRepository.getById(contractDto.getCompanyDto().getId())
            );
            contractDto.getCompanyDto().
                    setBankAccountDto(ModelDtoConverter.convertToListBankAccountDto(
                            bankAccountRepository.getBankAccountByCompanyId(contractDto.getCompanyDto().getId())));
            contractDto.setBankAccountDto(
                    bankAccountRepository.getById(contractDto.getBankAccountDto().getId())
            );
            contractDto.setContractorDto(
                    contractorRepository.getById(contractDto.getContractorDto().getId())
            );
            contractDto.setLegalDetailDto(
                    legalDetailRepository.getById(contractDto.getLegalDetailDto().getId())
            );
        }
        return listContractDto;
    }

    @Override
    public ContractDto getById(Long id) {
        ContractDto contractDto = contractRepository.getById(id);
        contractDto.setCompanyDto(
                companyRepository.getById(contractDto.getCompanyDto().getId())
        );
        contractDto.getCompanyDto().
                setBankAccountDto(ModelDtoConverter.convertToListBankAccountDto(
                        bankAccountRepository.getBankAccountByCompanyId(contractDto.getCompanyDto().getId())));
        contractDto.setBankAccountDto(
                bankAccountRepository.getById(contractDto.getBankAccountDto().getId())
        );
        contractDto.setContractorDto(
                contractorRepository.getById(contractDto.getContractorDto().getId())
        );
        contractDto.setLegalDetailDto(
                legalDetailRepository.getById(contractDto.getLegalDetailDto().getId())
        );
        return contractDto;
    }

    @Override
    public void create(ContractDto contractDto) {
        contractRepository.save(new Contract(
                SortNumberConverter.convert(contractDto.getNumber()),
                contractDto.getContractDate(),
                companyRepository.getOne(contractDto.getCompanyDto().getId()),
                bankAccountRepository.getOne(contractDto.getBankAccountDto().getId()),
                contractorRepository.getOne(contractDto.getContractorDto().getId()),
                contractDto.getAmount(),
                contractDto.getArchive(),
                contractDto.getComment(),
                legalDetailRepository.getOne(contractDto.getLegalDetailDto().getId())
        ));
    }

    @Override
    public void update(ContractDto contractDto) {
        contractRepository.save(new Contract(
                contractDto.getId(),
                SortNumberConverter.convert(contractDto.getNumber()),
                contractDto.getContractDate(),
                companyRepository.getOne(contractDto.getCompanyDto().getId()),
                bankAccountRepository.getOne(contractDto.getBankAccountDto().getId()),
                contractorRepository.getOne(contractDto.getContractorDto().getId()),
                contractDto.getAmount(),
                contractDto.getArchive(),
                contractDto.getComment(),
                legalDetailRepository.getOne(contractDto.getLegalDetailDto().getId())
        ));
    }

    @Override
    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }
}
