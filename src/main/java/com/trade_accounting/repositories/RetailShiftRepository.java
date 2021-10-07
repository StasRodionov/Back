package com.trade_accounting.repositories;

import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.RetailShift;
import com.trade_accounting.models.TechnicalOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailShiftRepository extends JpaRepository<RetailShift, Long>, JpaSpecificationExecutor<RetailShift> {

    @Query("from RetailShift t" +
            " where lower(concat(t.id, ' ', t.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<RetailShift> search(@Param("req") String request);
}
