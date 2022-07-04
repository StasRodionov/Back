package com.trade_accounting.repositories.util;


import com.trade_accounting.models.entity.util.Project;
import com.trade_accounting.models.dto.util.ProjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query("from Project s " +
            "where lower ( concat(s.id, ' ', s.name, ' ',s.code, ' ',s.description)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<Project> getBySearch(@Param("symbols") String search);

    @Query("select new com.trade_accounting.models.dto.util.ProjectDto(" +
            "e.id," +
            "e.name," +
            "e.code," +
            "e.description" +
            ") from Project e")
    List<ProjectDto> getAll();

    @Query("select new com.trade_accounting.models.dto.util.ProjectDto(" +
            "e.id," +
            "e.name," +
            "e.code," +
            "e.description" +
            ") from Project e")
    ProjectDto getById(@Param("id") Long id);
}
