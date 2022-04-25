package com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.MovementProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementProductRepository extends JpaRepository<MovementProduct, Long>,
        JpaSpecificationExecutor<MovementProduct> {
}
