package com.trade_accounting.repositories.util;

import com.trade_accounting.models.dto.util.ColumnsMaskDto;
import com.trade_accounting.models.entity.util.ColumnsMask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnsMaskRepository extends JpaRepository<ColumnsMask, Integer>, JpaSpecificationExecutor<ColumnsMask> {

    @Query("select new com.trade_accounting.models.dto.util.ColumnsMaskDto(" +
            "c.grid.id," +
            "c.employee.id," +
            "c.mask) " +
            "from ColumnsMask c " +
            "where c.grid.id = :grid_id and c.employee.id = :employee_id")
    ColumnsMaskDto getById(@Param("grid_id") Integer gridId, @Param("employee_id") Long employeeId);

}
