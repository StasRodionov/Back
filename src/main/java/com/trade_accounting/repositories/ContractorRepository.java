package com.trade_accounting.repositories;

import com.trade_accounting.models.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}
