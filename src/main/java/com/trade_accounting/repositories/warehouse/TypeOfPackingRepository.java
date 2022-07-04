package com.trade_accounting.repositories.warehouse;


import com.trade_accounting.models.entity.warehouse.TypeOfPacking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfPackingRepository extends JpaRepository<TypeOfPacking, Long> {
}
