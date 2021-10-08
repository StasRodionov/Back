package com.trade_accounting.repositories;

import com.trade_accounting.models.RetailSales;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.TechnicalOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailSalesRepository extends JpaRepository<RetailSales, Long>, JpaSpecificationExecutor<RetailSales> {

    @Query("from RetailSales t" +
            " where lower(concat(t.id, ' ', t.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<RetailSales> search(@Param("req") String request);
}
