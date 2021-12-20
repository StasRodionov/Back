package com.trade_accounting.repositories;

import com.trade_accounting.models.ShipmentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShipmentProductRepository extends JpaRepository<ShipmentProduct,Long>, JpaSpecificationExecutor<ShipmentProduct> {
}
