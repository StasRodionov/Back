package com.trade_accounting.repositories;

import com.trade_accounting.models.ReturnToSuppliers;
import com.trade_accounting.models.dto.ReturnToSuppliersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnToSuppliersRepository extends JpaRepository<ReturnToSuppliers, Long>, JpaSpecificationExecutor<ReturnToSuppliers> {

    @Query("select new com.trade_accounting.models.dto.ReturnToSuppliersDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnToSuppliers  e")
    List<ReturnToSuppliersDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ReturnToSuppliersDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnToSuppliers  e where  e.id = :id")
    ReturnToSuppliersDto getById(@Param("id") Long id);

    @Query(
            "select new com.trade_accounting.models.dto.ReturnToSuppliersDto (" +
                    "e.id," +
                    "e.date," +
                    "e.warehouse.id," +
                    "e.company.id," +
                    "e.contractor.id," +
                    "e.contract.id," +
                    "e.isSend," +
                    "e.isPrint, " +
                    "e.comment) from ReturnToSuppliers  e where  lower(e.comment) " +
                    "                                   like lower(concat('%', :nameFilter,'%'))"
    )
    List<ReturnToSuppliersDto> searchByNameFilter(@Param("nameFilter") String nameFilter);

    @Query("select new com.trade_accounting.models.dto.ReturnToSuppliersDto (" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.isSend," +
            "e.isPrint, " +
            "e.comment) from ReturnToSuppliers  e where  e.id = :nameFilter "
    )
    List<ReturnToSuppliersDto> searchById(@Param("nameFilter") Long nameFilter);


}
