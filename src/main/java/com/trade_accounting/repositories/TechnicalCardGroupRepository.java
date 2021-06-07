package com.trade_accounting.repositories;

import com.trade_accounting.models.TechnicalCardGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalCardGroupRepository extends JpaRepository<TechnicalCardGroup, Long> {
}
