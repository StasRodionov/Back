package com.trade_accounting.repositories;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
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




    @Query(
            "from Contract c LEFT OUTER JOIN BankAccount AS cg " +
            "ON c.bankAccount.id =  cg.id " +
            "LEFT OUTER JOIN Contractor as top " +
            "ON c.contractor.id =  top.id " +
            "LEFT OUTER JOIN Company as address " +
            "ON c.company.id =  address.id " +
            "LEFT OUTER JOIN LegalDetail as ld " +
            "ON c.legalDetail.id =  ld.id " +
            " where lower(c.contractor.name) like lower(concat('%', :searchContr, '%'))"
    )
    List<Contract> search(@Param("searchContr") String searchContr);
}
