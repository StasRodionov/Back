package com.trade_accounting.repositories;

import com.trade_accounting.models.ContractorGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorGroupRepository extends JpaRepository<ContractorGroup, Long> {
}
