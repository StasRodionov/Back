package com.trade_accounting.repositories;


import com.trade_accounting.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long>, JpaSpecificationExecutor<File> {
    @Query("select new com.trade_accounting.models.File(" +
            "i.id, " +
            "i.name, " +
            "i.extension, " +
            "i.placement, " +
            "i.employee, " +
            "i.key, " +
            "i.uploadDateTime) from File as i")
    List<File> getAll(File file);

}
