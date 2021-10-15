package com.trade_accounting.repositories;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmail(String email);

    @Query("from Employee e " +
            "where lower ( concat(e.firstName, ' ', e.middleName, ' ', e.lastName, ' ', e.email, ' ', e.phone, ' ', e.department.name, ' ', e.position.name)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<Employee> getBySearch(@Param("symbols") String search);

}
