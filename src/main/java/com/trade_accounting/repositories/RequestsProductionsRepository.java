package com.trade_accounting.repositories;

import com.trade_accounting.models.RequestsProductions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsProductionsRepository extends JpaRepository<RequestsProductions,Long>,
        JpaSpecificationExecutor<RequestsProductions> {
}
