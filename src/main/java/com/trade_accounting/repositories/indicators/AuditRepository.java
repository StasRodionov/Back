package com.trade_accounting.repositories.indicators;

import com.trade_accounting.models.entity.Indicators.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {


}
