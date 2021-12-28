package com.trade_accounting.repositories;

import com.trade_accounting.models.Movement;
import com.trade_accounting.models.PurchaseControl;
import com.trade_accounting.models.PurchaseHistoryOfSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseHistoryOfSalesRepository extends JpaRepository<PurchaseHistoryOfSales, Long>,
        JpaSpecificationExecutor<PurchaseHistoryOfSales> {

    @Query("SELECT c FROM PurchaseHistoryOfSales c")
    List<PurchaseHistoryOfSales> findAll();


    @Query("SELECT c FROM PurchaseHistoryOfSales c WHERE c.id = :id")
    PurchaseHistoryOfSales getOne(@Param("id") Long id);

}
