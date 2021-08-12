package com.trade_accounting.repositories;

import com.trade_accounting.models.MoneySubProfitLoss;
import com.trade_accounting.models.dto.MoneySubProfitLossDto;
import com.trade_accounting.models.dto.ReturnToSupplierDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneySubProfitLossRepository extends JpaRepository<MoneySubProfitLoss, Long>,
        JpaSpecificationExecutor<MoneySubProfitLoss> {

    @Query("select new com.trade_accounting.models.dto.MoneySubProfitLossDto (" +
            "e.id," +
            "e.itemsList," +
            "e.profitLoss) from MoneySubProfitLoss  e")
    List<MoneySubProfitLossDto> getAll();

    @Query("select new com.trade_accounting.models.dto.MoneySubProfitLossDto (" +
            "e.id," +
            "e.itemsList," +
            "e.profitLoss) from MoneySubProfitLoss  e where  e.id = :id")
    ReturnToSupplierDto getById(@Param("id") Long id);
}