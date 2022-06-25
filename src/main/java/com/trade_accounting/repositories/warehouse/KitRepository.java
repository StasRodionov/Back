package com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface KitRepository  extends JpaRepository<Kit, Long>, JpaSpecificationExecutor<Kit> {

}
