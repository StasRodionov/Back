package com.trade_accounting.repositories.units;

import com.trade_accounting.models.entity.units.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario,Long>, JpaSpecificationExecutor<Scenario> {


    @Query("from Scenario s " +
            "where lower ( concat(s.id, ' ', s.name, ' ',s.comment, ' ',s.activeStatus)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<Scenario> getBySearch(@Param("symbols") String search);
}
