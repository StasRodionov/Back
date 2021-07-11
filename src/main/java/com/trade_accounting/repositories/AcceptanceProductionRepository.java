package com.trade_accounting.repositories;

import com.trade_accounting.models.AcceptanceProduction;
import com.trade_accounting.models.Correction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AcceptanceProductionRepository extends JpaRepository<AcceptanceProduction, Long>,
        JpaSpecificationExecutor<AcceptanceProduction> {
}
