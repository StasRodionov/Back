package com.trade_accounting.repositories;

import com.trade_accounting.models.ReturnsToSuppliers;
import com.trade_accounting.models.dto.ReturnsToSuppliersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnsToSuppliersRepository extends JpaRepository<ReturnsToSuppliers, Long>, JpaSpecificationExecutor<ReturnsToSuppliers> {

    @Query("select new com.trade_accounting.models.dto.ReturnsToSuppliersDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnsToSuppliers  e")
    List<ReturnsToSuppliersDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ReturnsToSuppliersDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnsToSuppliers  e where  e.id = :id")
    ReturnsToSuppliersDto getById(@Param("id") Long id);

    @Query(
            "select new com.trade_accounting.models.dto.ReturnsToSuppliersDto (" +
                    "e.id," +
                    "e.date," +
                    "e.warehouse.id," +
                    "e.company.id," +
                    "e.contractor.id," +
                    "e.contract.id," +
                    "e.isSend," +
                    "e.isPrint, " +
                    "e.comment) from ReturnsToSuppliers  e where  lower(e.comment) " +
                    "                                   like lower(concat('%', :nameFilter,'%'))"
    )
    List<ReturnsToSuppliersDto> searchByNameFilter(@Param("nameFilter") String nameFilter);

    @Query("select new com.trade_accounting.models.dto.ReturnsToSuppliersDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnsToSuppliers  e where  e.id = :nameFilter "
    )
    List<ReturnsToSuppliersDto> searchById(@Param("nameFilter") Long nameFilter);


}
