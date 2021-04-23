package com.trade_accounting.repositories;

import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailStoreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailStoreRepository extends JpaRepository<RetailStore, Long> {

    @Query("select new com.trade_accounting.models.dto.RetailStoreDto(" +
            "e.id, e.name, e.isActive, e.activityStatus, e.revenue) " +
            "from RetailStore e")
    List<RetailStoreDto> getAll();

    @Query("select new com.trade_accounting.models.dto.RetailStoreDto(" +
            "e.id, e.name, e.isActive, e.activityStatus, e.revenue) " +
            "from RetailStore e where e.id = :id")
    RetailStoreDto getById(@Param("id") Long id);

}
