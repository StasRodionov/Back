package com.trade_accounting.repositories;

import com.trade_accounting.models.TechnicalOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface TechnicalOperationsRepository extends JpaRepository<TechnicalOperations, Long>, JpaSpecificationExecutor<TechnicalOperations> {

}
