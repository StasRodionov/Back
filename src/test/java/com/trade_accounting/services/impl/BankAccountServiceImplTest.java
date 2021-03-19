package com.trade_accounting.services.impl;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Test
    void getAll_shouldReturnListFilledBankAccountDto() {
        when(bankAccountRepository.findAll())
                .thenReturn(getListBankAccountFromRepo());

        List<BankAccountDto> bankAccounts = bankAccountService.getAll();

        assertNotNull(bankAccounts, "failure - expected that a list of bankAccountDto not null");
        assertTrue(bankAccounts.size() > 0, "failure - expected that a list of bankAccountDto grater than 0");

        for(BankAccountDto bankAccount : bankAccounts) {
            bankAccountDtoIsCorrectlyInited(bankAccount);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListBankAccountDto() {
        when(bankAccountRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<BankAccountDto> bankAccounts = bankAccountService.getAll();

        assertNotNull(bankAccounts, "failure - expected that a list of bankAccountDto not null");
        assertEquals(0, bankAccounts.size(), "failure - expected that size of list of bankAccountDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledBankAccountDto() {
        Optional<BankAccount> bankAccountFromRepo = Optional.of(getBankAccountFromRepo(1L));

        when(bankAccountRepository.findById(anyLong()))
                .thenReturn(bankAccountFromRepo);

        BankAccountDto bankAccount = bankAccountService.getById(1L);

        assertNotNull(bankAccount, "failure - expected that bankAccount not null");
        bankAccountDtoIsCorrectlyInited(bankAccount);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        bankAccountService.create(
                getBankAccountDto(1L)
        );

        verify(bankAccountRepository).save(any(BankAccount.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        bankAccountService.update(
                getBankAccountDto(1L)
        );

        verify(bankAccountRepository).save(any(BankAccount.class));
    }

    @Test
    void deleteById() {
        bankAccountService.deleteById(1L);
        verify(bankAccountRepository).deleteById(1L);
    }

    void bankAccountDtoIsCorrectlyInited(BankAccountDto bankAccount) {
        assertNotNull(bankAccount, "Fail in passed employee");
        assertNotNull(bankAccount.getId(), "Fail in field 'id' of bankAccount");
        assertNotNull(bankAccount.getName(), "Fail in field 'name' of bankAccount");
    }
}