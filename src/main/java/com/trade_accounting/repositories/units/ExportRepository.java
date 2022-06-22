package com.trade_accounting.repositories.units;

import com.trade_accounting.models.entity.units.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportRepository extends JpaRepository<Export,Long>, JpaSpecificationExecutor<Export> {
}
