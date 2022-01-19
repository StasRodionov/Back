package com.trade_accounting.repositories;

import com.trade_accounting.models.PurchaseCurrentBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseCurrentBalanceRepository extends JpaRepository<PurchaseCurrentBalance, Long> {


    @Query("SELECT c FROM PurchaseCurrentBalance c")
    List<PurchaseCurrentBalance> findAll();


    @Query("SELECT c FROM PurchaseHistoryOfSales c WHERE c.id = :id")
    PurchaseCurrentBalance getOne(@Param("id") Long id);
}
