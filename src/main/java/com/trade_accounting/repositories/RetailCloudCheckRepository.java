package com.trade_accounting.repositories;

import com.trade_accounting.models.RetailCloudCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RetailCloudCheckRepository extends JpaRepository<RetailCloudCheck,Long>, JpaSpecificationExecutor<RetailCloudCheck> {
}
