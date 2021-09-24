package com.trade_accounting.repositories;

import com.trade_accounting.models.RetailMaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RetailMakingRepository extends JpaRepository<RetailMaking, Long>, JpaSpecificationExecutor<RetailMaking> {
}
