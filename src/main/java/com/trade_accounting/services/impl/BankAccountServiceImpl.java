package com.trade_accounting.services.impl;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.services.interfaces.BankAccountService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CompanyRepository companyRepository;

    private final DtoMapper dtoMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, CompanyRepository companyRepository, DtoMapper dtoMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.companyRepository = companyRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public BankAccountDto getBankByBic(String bic) {
        return bankAccountRepository.getBankByBic(bic).get(0);
    }

    @Override
    public List<String> getBankUniqueBic() {
        return bankAccountRepository.getListBankUniqueBic();
    }

    @Override
    public List<BankAccountDto> getAll() {
        return bankAccountRepository.findAll().stream()
                .map(dtoMapper::bankAccountToBankAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountDto getById(Long id) {
        return dtoMapper.bankAccountToBankAccountDto(
                bankAccountRepository.findById(id).orElse(new BankAccount())
        );
    }

    @Override
    public BankAccountDto create(BankAccountDto dto) {
        BankAccount bankAccount = bankAccountRepository.save(
                dtoMapper.bankAccountDtoToBankAccount(dto)
        );
        dto.setId(bankAccount.getId());
        return dtoMapper.bankAccountToBankAccountDto(bankAccount);
    }

    @Override
    public BankAccountDto update(BankAccountDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        BankAccount bankAccount = bankAccountRepository.getOne(id);
        Company company = companyRepository.getCompanyByBankAccounts(bankAccount);
        List<BankAccount> newBankAccountList = new ArrayList<>();
        for (BankAccount item : company.getBankAccounts()) {
            if (!item.equals(bankAccount)) {
                newBankAccountList.add(item);
            }
        }
        company.setBankAccounts(newBankAccountList);
        companyRepository.save(company);
        bankAccountRepository.deleteById(id);
    }
}
