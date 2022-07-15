package com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>, JpaSpecificationExecutor<Services> {

}
