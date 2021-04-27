package com.trade_accounting.services.impl;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.services.interfaces.BankAccountService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final DtoMapper dtoMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, DtoMapper dtoMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public BankAccountDto getBankByBic(String bic) {
        return bankAccountRepository.getBankByBic(bic);
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

        return dtoMapper.bankAccountToBankAccountDto(bankAccount);
    }

    @Override
    public BankAccountDto update(BankAccountDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
