package com.trade_accounting.repositories.units;

import com.trade_accounting.models.entity.units.Import;
import com.trade_accounting.models.entity.units.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportRepository extends JpaRepository<Import, Long>, JpaSpecificationExecutor<Import> {

    @Query("from Import i " +
            "where lower ( concat(i.id, ' ', i.task, ' ',i.start, ' ',i.end, ' ',i.status)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<Import> getBySearch(@Param("symbols") String search);

}
