package com.trade_accounting.repositories;

import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.dto.ProductGroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

    @Query("select new com.trade_accounting.models.dto.ProductGroupDto(" +
            "pg.id, " +
            "pg.name, " +
            "pg.sortNumber) from ProductGroup  pg")
    List<ProductGroupDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ProductGroupDto(" +
            "pg.id, " +
            "pg.name, " +
            "pg.sortNumber) from ProductGroup  pg where pg.id = :id")
    ProductGroupDto getById(@Param("id") Long id);
}
