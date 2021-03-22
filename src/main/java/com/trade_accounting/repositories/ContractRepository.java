package com.trade_accounting.repositories;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.ContractDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long>, JpaSpecificationExecutor<Contract> {

    @Query("select new com.trade_accounting.models.dto.ContractDto(" +
            "e.id," +
            "e.number," +
            "e.contractDate," +
            "e.company.id," +
            "e.bankAccount.id," +
            "e.contractor.id," +
            "e.amount," +
            "e.archive," +
            "e.comment," +
            "e.legalDetail.id) from Contract e")
    List<ContractDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ContractDto(" +
            "e.id," +
            "e.number," +
            "e.contractDate," +
            "e.company.id," +
            "e.bankAccount.id," +
            "e.contractor.id," +
            "e.amount," +
            "e.archive," +
            "e.comment," +
            "e.legalDetail.id) from Contract e where e.id = :id")
    ContractDto getById(@Param("id") Long id);
}
