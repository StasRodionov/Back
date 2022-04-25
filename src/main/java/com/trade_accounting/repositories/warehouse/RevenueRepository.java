package com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}
