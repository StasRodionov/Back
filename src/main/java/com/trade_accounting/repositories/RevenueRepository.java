package com.trade_accounting.repositories;

import com.trade_accounting.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}
