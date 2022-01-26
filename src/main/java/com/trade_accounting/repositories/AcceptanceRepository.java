package com.trade_accounting.repositories;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.Correction;
import com.trade_accounting.models.dto.AcceptanceDto;
import com.trade_accounting.models.dto.ReturnToSupplierDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptanceRepository extends JpaRepository<Acceptance, Long>, JpaSpecificationExecutor<Acceptance> {
    @Query("from Acceptance a" +
            " where lower(concat(a.id, ' ', a.comment))" +
            " like lower(concat('%', :req, '%'))")
    List<Acceptance> search(@Param("req") String request);
}
