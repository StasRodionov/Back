package com.trade_accounting.repositories;

import com.trade_accounting.models.dto.ProductionDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<ProductionDto, Long> {

}
