package com.trade_accounting.repositories;

import com.trade_accounting.models.ReturnsToSupplier;
import com.trade_accounting.models.dto.ReturnsToSupplierDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnsToSupplierRepository extends JpaRepository<ReturnsToSupplier, Long>, JpaSpecificationExecutor<ReturnsToSupplier> {

    @Query("select new com.trade_accounting.models.dto.ReturnsToSupplierDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnsToSupplier  e")
    List<ReturnsToSupplierDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ReturnsToSupplierDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnsToSupplier  e where  e.id = :id")
    ReturnsToSupplierDto getById(@Param("id") Long id);

    @Query(
            "select new com.trade_accounting.models.dto.ReturnsToSupplierDto (" +
                    "e.id," +
                    "e.date," +
                    "e.warehouse.id," +
                    "e.company.id," +
                    "e.contractor.id," +
                    "e.contract.id," +
                    "e.isSend," +
                    "e.isPrint, " +
                    "e.comment) from ReturnsToSupplier  e where  lower(e.comment) " +
                    "                                   like lower(concat('%', :nameFilter,'%'))"
    )
    List<ReturnsToSupplierDto> searchByNameFilter(@Param("nameFilter") String nameFilter);

    @Query("select new com.trade_accounting.models.dto.ReturnsToSupplierDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnsToSupplier  e where  e.id = :nameFilter "
    )
    List<ReturnsToSupplierDto> searchById(@Param("nameFilter") Long nameFilter);


}
