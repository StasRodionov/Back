package com.trade_accounting.repositories;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.TechnicalOperations;
import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.models.dto.TechnicalOperationsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TechnicalOperationsRepository extends JpaRepository<TechnicalOperations, Long>, JpaSpecificationExecutor<TechnicalOperations> {


    @Query("from TechnicalOperations t" +
            " where lower(concat(t.id, ' ', t.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<TechnicalOperations> search(@Param("req") String request);
}
