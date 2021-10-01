package com.trade_accounting.repositories;

import com.trade_accounting.models.Prepayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrepayoutRepository extends JpaRepository<Prepayout, Long>, JpaSpecificationExecutor<Prepayout> {
}
