package com.trade_accounting.repositories;

import com.trade_accounting.models.TypeOfPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfPriceRepository extends JpaRepository<TypeOfPrice, Long> {
}
