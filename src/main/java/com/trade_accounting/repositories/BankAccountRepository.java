package com.trade_accounting.repositories;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.dto.BankAccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("select new com.trade_accounting.models.dto.BankAccountDto(" +
            "ba.id, " +
            "ba.rcbic, " +
            "ba.bank, " +
            "ba.address, " +
            "ba.correspondentAccount, " +
            "ba.account, " +
            "ba.mainAccount, " +
            "ba.sortNumber) from BankAccount ba")
    List<BankAccountDto> getAll();

    @Query("select new com.trade_accounting.models.dto.BankAccountDto(" +
            "ba.id, " +
            "ba.rcbic, " +
            "ba.bank, " +
            "ba.address, " +
            "ba.correspondentAccount, " +
            "ba.account, " +
            "ba.mainAccount, " +
            "ba.sortNumber) from BankAccount ba " +
            "where ba.id = :id")
    BankAccountDto getById(@Param("id") Long id);

    @Query("select ba.bankAccounts from Contractor ba where ba.id = :id")
  List<BankAccount>  getBankAccountByContractorId(@Param("id") Long id);
}
