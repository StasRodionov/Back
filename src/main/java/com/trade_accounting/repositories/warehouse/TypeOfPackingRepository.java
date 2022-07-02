package com.trade_accounting.repositories.warehouse;


import com.trade_accounting.models.entity.warehouse.TypeOfPacking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfPackingRepository extends JpaRepository<TypeOfPacking, Long> {

    @Override
    @Query("SELECT c FROM TypeOfPacking c WHERE c.id = :id")
    TypeOfPacking getOne(@Param("id") Long id);
}
