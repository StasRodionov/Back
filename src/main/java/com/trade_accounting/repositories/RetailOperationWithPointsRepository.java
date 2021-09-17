package com.trade_accounting.repositories;

import com.trade_accounting.models.RetailOperationWithPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailOperationWithPointsRepository extends JpaRepository<RetailOperationWithPoints,Long> {
}
