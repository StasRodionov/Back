package com.trade_accounting.repositories;

import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.RoleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select new com.trade_accounting.models.dto.RoleDto(r.id, r.name, r.sortNumber) from Role r")
    List<RoleDto> getAll();

    @Query("select new com.trade_accounting.models.dto.RoleDto(r.id, r.name, r.sortNumber) from Role r where r.id = :id")
    RoleDto getById(@Param("id") Long id);

}
