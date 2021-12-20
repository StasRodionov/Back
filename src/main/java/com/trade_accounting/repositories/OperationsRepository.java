package com.trade_accounting.repositories;

import com.trade_accounting.models.OperationsAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperationsRepository extends JpaRepository<OperationsAbstract,Long>, JpaSpecificationExecutor<OperationsAbstract> {
}
