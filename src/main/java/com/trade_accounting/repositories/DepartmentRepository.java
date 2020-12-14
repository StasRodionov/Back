package com.trade_accounting.repositories;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.dto.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select new com.trade_accounting.models.dto.DepartmentDto(" +
            "d.id, " +
            "d.name, " +
            "d.sortNumber" +
            ") " +
            "from Department d")
    List<DepartmentDto> getAll();

    @Query("select new com.trade_accounting.models.dto.DepartmentDto(" +
            "d.id, " +
            "d.name, " +
            "d.sortNumber" +
            ") " +
            "from Department d " +
            "where d.id = :id")
    DepartmentDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.DepartmentDto(" +
            "em.department.id, " +
            "em.department.name, " +
            "em.department.sortNumber" +
            ") " +
            "from Employee em " +
            "where em.id = :id")
    DepartmentDto getDepartmentByEmployeeId(@Param("id") Long id);
}
