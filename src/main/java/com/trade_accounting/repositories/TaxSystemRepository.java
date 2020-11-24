package com.trade_accounting.repositories;

import com.trade_accounting.models.TaxSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxSystemRepository extends JpaRepository<TaxSystem, Long> {
}
