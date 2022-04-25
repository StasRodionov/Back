package com.trade_accounting.repositories.production;

import com.trade_accounting.models.entity.production.ProductionTargets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionTargetsRepository extends JpaRepository<ProductionTargets, Long>,
        JpaSpecificationExecutor<ProductionTargets> {

    @Query("from ProductionTargets t" +
            " where lower(concat(t.id, ' ', t.description))" +
            " like lower(concat('%', :req, '%'))")
    List<ProductionTargets> search(@Param("req") String request);

}