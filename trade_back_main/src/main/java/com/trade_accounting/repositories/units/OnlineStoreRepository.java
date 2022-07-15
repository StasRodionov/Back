package com.trade_accounting.repositories.units;

import com.trade_accounting.models.dto.units.OnlineStoreDto;
import com.trade_accounting.models.entity.units.OnlineStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineStoreRepository extends JpaRepository<OnlineStore, Long>, JpaSpecificationExecutor<OnlineStore> {

    @Query("SELECT NEW com.trade_accounting.models.dto.units.OnlineStoreDto(" +
            "onlineStore.id," +
            "onlineStore.name," +
            "onlineStore.type," +
            "onlineStore.orders," +
            "onlineStore.remains) FROM OnlineStore onlineStore")
    List<OnlineStoreDto> getAll();

    @Query("SELECT NEW com.trade_accounting.models.dto.units.OnlineStoreDto(" +
            "onlineStore.id," +
            "onlineStore.name," +
            "onlineStore.type," +
            "onlineStore.orders," +
            "onlineStore.remains) FROM OnlineStore onlineStore " +
            "WHERE onlineStore.id = :id")
    OnlineStoreDto getById(@Param("id") Long id);

    @Query("FROM OnlineStore o " +
            "WHERE LOWER (CONCAT(o.name, ' ', o.orders, ' ', o.remains)) " +
            "LIKE LOWER (CONCAT('%', :symbols, '%'))")
    List<OnlineStore> searchByString(@Param("symbols") String text);
}
