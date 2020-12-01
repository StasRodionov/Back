package com.trade_accounting.repositories;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.dto.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
