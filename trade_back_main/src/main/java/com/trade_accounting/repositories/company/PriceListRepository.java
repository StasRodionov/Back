package com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long>, JpaSpecificationExecutor<PriceList> {

    @Query("from PriceList e " +
            "where lower ( concat(e.number, ' ', e.comment)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<PriceList> getBySearch(@Param("symbols") String search);
}
