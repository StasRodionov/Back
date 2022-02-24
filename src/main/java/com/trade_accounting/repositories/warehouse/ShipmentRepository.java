package com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.warehouse.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long>, JpaSpecificationExecutor<Shipment> {

    @Query("select s from Shipment s where lower(concat(s.id, ' ', s.contractor.id)) like lower(concat('%', :nameFilter, '%'))")
    List<Shipment> searchByIdAndNameFilter(@Param("nameFilter") String nameFilter);
}
