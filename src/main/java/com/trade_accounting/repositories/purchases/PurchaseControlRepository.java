package com.trade_accounting.repositories.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseControlRepository extends JpaRepository<PurchaseControl, Long>, JpaSpecificationExecutor<PurchaseControl> {


}
