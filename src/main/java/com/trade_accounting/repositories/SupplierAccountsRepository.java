package com.trade_accounting.repositories;

import com.trade_accounting.models.SupplierAccounts;
import com.trade_accounting.models.dto.SupplierAccountsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAccountsRepository extends JpaRepository<SupplierAccounts,Long>,
                                                      JpaSpecificationExecutor<SupplierAccounts> {

    @Query("select new com.trade_accounting.models.dto.SupplierAccountsDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.warehouse.id," +
            "e.contract.id," +
            "e.isSpend," +
            "e.comment) from SupplierAccounts  e")
    List<SupplierAccountsDto> getAll();


    @Query("select new com.trade_accounting.models.dto.SupplierAccountsDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.warehouse.id," +
            "e.contract.id," +
            "e.isSpend," +
            "e.comment) from SupplierAccounts  e where  e.id = :id")
    SupplierAccountsDto getById(@Param("id") Long id);
}
